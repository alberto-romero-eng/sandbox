package localhost.k8sjclient.service;

import java.util.Arrays;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodList;
import io.kubernetes.client.util.Config;

public class K8sJClientService {

	public static void main(String[] args) {
		System.out.println("K8sJClientService -- Start main() -- args: " + Arrays.asList(args));
		try {
			getPods(args);
		} catch (Throwable ex) {
			System.out.println("exception -- " + ex);
		}

	}

	private static void getPods(String[] args) throws Throwable {
		System.out.println("K8sJClientService -- Start getPods() -- args: " + Arrays.asList(args));

		ApiClient client = Config.fromConfig("kc-wm0-pre.yml");
		client.setVerifyingSsl(false);
		Configuration.setDefaultApiClient(client);

		CoreV1Api api = new CoreV1Api();
		V1PodList podList = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);

		for (V1Pod pod : podList.getItems()) {
			System.out.println(pod.getMetadata().getName());
		}

	}
}
