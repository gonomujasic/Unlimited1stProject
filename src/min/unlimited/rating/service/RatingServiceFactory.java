package min.unlimited.rating.service;

import java.util.HashMap;
import java.util.Map;

public class RatingServiceFactory {

	 

	private static class RSFSingleton{
		
		private static final RatingServiceFactory INSTANCE = new RatingServiceFactory();
		private static final Map<String, RatingService> SERVICES = new HashMap<String, RatingService>();
	}

	public RatingServiceFactory() {
	}

	public static RatingServiceFactory getInstance(){
		
		return RSFSingleton.INSTANCE;
		
	}
	
	public static Map<String, RatingService> getMapInstance(){
		
		return RSFSingleton.SERVICES;
	}
	
	public void loadServices(){
		Map<String, RatingService> SERVICES = getMapInstance();
		if(SERVICES.isEmpty()){
			
			getMapInstance().put("do_rating", new DoRatingService());
			getMapInstance().put("received_rating_list", new ReceivedRatingService());
			getMapInstance().put("given_rating_list", new GivenRatingService());
			getMapInstance().put("delete_rating", new DeleteRatingService());
			
		}
		
	}
	
	public RatingService getRatingService(String command){
		
		RatingService service = null;
		
		loadServices();
		service = getMapInstance().get(command);
		
		return service;
		
	}
	
}
