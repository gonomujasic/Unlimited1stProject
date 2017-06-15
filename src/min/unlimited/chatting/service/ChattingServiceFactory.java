package min.unlimited.chatting.service;

import java.util.HashMap;
import java.util.Map;

public class ChattingServiceFactory {

	private static class CSFSingleton{
		
		private static final Map<String,ChattingService> SERVICES = new HashMap<String,ChattingService>();
		private static final ChattingServiceFactory INSTANCE = new ChattingServiceFactory();
	}
	
	public static Map<String,ChattingService> getMapInstance(){
		
		return CSFSingleton.SERVICES;
	}
	
	public static ChattingServiceFactory getInstance(){
		
		return CSFSingleton.INSTANCE;
	}
	
	public void loadService(){
		
		Map<String,ChattingService> SERVICES = getMapInstance();
		
		if(SERVICES.isEmpty()){
			
			SERVICES.put("loadlist", new LoadListService());
			SERVICES.put("newchat", new NewChattingService());
			SERVICES.put("enterchat", new EnterChattingService());
			SERVICES.put("loadinfo", new LoadInfoService());
			SERVICES.put("chathistory", new ChatHistoryService());
			SERVICES.put("searchlist", new SearchListService());
			SERVICES.put("closechat", new CloseChatService());
			SERVICES.put("mentorCheck", new CheckMentorService());
			
		}
		
	}
	
	public ChattingService getChattingService(String command){
		
		ChattingService service = null;
		
		loadService();
		service = CSFSingleton.SERVICES.get(command);
		
		return service;
		
	}
	
}
