import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.networking.v1.IngressBuilder;
import io.fabric8.kubernetes.client.utils.KubernetesSerialization;

///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS io.fabric8:kubernetes-client:6.9.2
public class IngressDefinition {
  public static void main(String... args) {
    final var metadata = new ObjectMetaBuilder()
      .withName("ingress-name")
      .build();
    final var ingress = new IngressBuilder()
      .withMetadata(metadata)
      .withNewSpec()
      .addNewRule()
      .withHost(metadata.getName() + ".127.0.0.1.nip.io")
      .withNewHttp()
      .addNewPath()
      .withPath("/")
      .withPathType("Prefix")
      .withNewBackend()
      .withNewService()
      .withName(metadata.getName())
      .withNewPort().withNumber(8080).endPort()
      .endService()
      .endBackend()
      .endPath()
      .endHttp()
      .endRule()
      .endSpec()
      .build();
    KubernetesSerialization ks = new KubernetesSerialization();
    System.out.println(ks.asYaml(ingress));
  }
}
