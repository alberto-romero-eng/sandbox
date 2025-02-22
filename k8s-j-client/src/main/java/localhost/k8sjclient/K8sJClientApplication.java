package localhost.k8sjclient;

import java.util.Arrays;

import localhost.k8sjclient.service.K8sJClientService;

public class K8sJClientApplication {

	public static void main(String[] args) {
		System.out.println("K8sJClientApplication -- Start main() -- args: " + Arrays.asList(args));
		K8sJClientService.main(args);
	}
}
