public class Client {
	public static void main(String[] args) {
		Volume volume = new Protection();
        System.out.println("[Client] call getText");
		System.out.println(volume.getText());

        System.out.println("[Client] call append");
		volume.append("Hello world.");
	}
}
