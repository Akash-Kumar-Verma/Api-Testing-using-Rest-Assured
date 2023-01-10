package RestFramework.Utilities;

public class Resources {
	public static String key = "95347f4cc645e7f0c0a6c858f7f40273";
	public static String token = "9f041e089e8235a9dd5bae07077c7ba65c89987eda71d51323b57a1f49c1bf42";
    
	public static String idBoard = "636e1e16bc4e420285b79264";
	public static String idList = "636e1e16bc4e420285b7926b";
	public static String idCard = "63736c5be5ee9a017276f59c";
	

	
	public static String EndPoints="?key=" + key + "&token=" + token;
	
	public static String BoardpostEndPoint = "/1/boards/?name=Akash Verma&key=" + key + "&token=" + token;
	public static String CardpostEndPoint="/1/cards?idList=" + idList + "&key=" + key + "&token=" + token;
	public static String CheckListpostEndPoint="/1/checklists?idCard=" + idCard + "&key=" + key + "&token=" + token;
	public static String LebelpostEndPoint="/1/labels?idBoard=" + idBoard + "&key=" + key + "&token=" + token;
}
