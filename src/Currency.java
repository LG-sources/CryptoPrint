import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpConnectTimeoutException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class Currency {
	private String assetId;
	private String name;
	private double currValueUSD;
	private double volume1hUSD;
	private double volume1dUSD;
	private double volume1mUSD;
	private final String URL = "https://rest.coinapi.io/v1/assets/";
	
	public Currency(String asset) throws JsonParseException, IOException, InterruptedException {
		this.assetId = asset;
		this.name = "";
		this.currValueUSD = 0;
		this.volume1dUSD=0;
		this.volume1hUSD=0;
		this.volume1mUSD=0;
		this.setValue();
	}
	
	@SuppressWarnings("deprecation")
	private void setValue() {
		try {
			HttpClient client = HttpClient.newHttpClient();
			URI uri = new URI(URL+this.assetId); 
			System.out.println("\nYou are trying to access : "+uri);
			HttpRequest request = HttpRequest.newBuilder()
					.uri(uri)
					.header("X-CoinAPI-Key", "410E6ED9-9777-4B7F-A673-1FC54CEB824A")
					.timeout(Duration.ofSeconds(15))
					.build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
			if(response.statusCode()==200) {
				System.out.println("The query issued a response, here are the results :\n");
			}

			
			String json_string = response.body().replace("[", "").replace("]", "");
			
			
			JsonParser parser = new JsonParser();
			if(json_string.contains("{")) {
				JsonObject object = (JsonObject) parser.parse(json_string);
				if(object.get("name")!=null)
					this.name = object.get("name").getAsString();
				else
					this.name = "No name";
				if(object.get("price_usd")!=null)
					this.currValueUSD = object.get("price_usd").getAsDouble();
				else
					this.currValueUSD = -1;
				if(object.get("volume_1hrs_usd")!=null)
					this.volume1hUSD = object.get("volume_1hrs_usd").getAsDouble();
				else 
					this.volume1hUSD = -1;
				if(object.get("volume_1day_usd")!=null)
					this.volume1dUSD = object.get("volume_1day_usd").getAsDouble();
				else 
					this.volume1dUSD = -1;
				if(object.get("volume_1mth_usd")!=null)
					this.volume1mUSD = object.get("volume_1mth_usd").getAsDouble();
				else
					this.volume1mUSD = -1;
			}
			else {
				System.out.println("\nAn error occured while the program tried to reach certain informations\nPlease make sure that the id is correctly spelled.");
				this.name = "Error";
				this.currValueUSD = -1;
				this.volume1dUSD = -1;
				this.volume1hUSD = -1;
				this.volume1mUSD = -1;
			}
		}
		catch(HttpConnectTimeoutException http) {
			System.out.println("Connexion failed, request timeout.\n");
			http.printStackTrace();
		}
		catch(URISyntaxException U) {
			System.out.println("Asset ID may be misspelled.\n");
			U.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
	}
	
	private String getAsset() {
		return this.assetId;
	}
	
	private double getCurrValue(){
		return this.currValueUSD;
	}
	private double get1hVolume() {
		return this.volume1hUSD;
	}
	private double get1dVolume() {
		return this.volume1dUSD;
	}
	private double get1mVolume() {
		return this.volume1mUSD;
	}
	private String getName() {
		return this.name;
	}
	
	public String toString() {
		if(this.name.equals("Error")) {
			return "";
		}
			String res = "";
			res+="\t\t*======================================*\n";
			res+="\t\t| ASSET ID : "+this.getAsset()+" \t\t\t|\n";
			res+="\t\t| NAME : "+this.getName()+"\t\t\t|\n";
			res+="\t\t| CURRENT VALUE : "+this.getCurrValue()+" $ \t|\n";
			res+="\t\t| 1H VOLUME : "+this.get1hVolume()+" $ \t|\n";
			res+="\t\t| 1D VOLUME : "+this.get1dVolume()+" $ \t|\n";
			res+="\t\t| 1M VOLUME ; "+this.get1mVolume()+" $ \t|\n";
			res+="\t\t| DATA AT : "+java.time.LocalTime.now()+" \t\t|\n";
			res+="\t\t| THE : "+java.time.LocalDate.now()+" \t\t\t|\n";
			res+="\t\t*======================================*\n";
			return res;
	}
}
