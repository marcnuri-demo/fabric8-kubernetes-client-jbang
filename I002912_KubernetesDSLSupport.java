///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS io.fabric8:kubernetes-client:5.4-SNAPSHOT

import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

import java.util.Optional;
import java.util.function.Consumer;

public class I002912_KubernetesDSLSupport {

  public static void main(String... args) {
    try (final KubernetesClient kc = new DefaultKubernetesClient()) {
      final Consumer<HasMetadata> print = hm -> System.out.printf("- %s %s%n",
        Optional.ofNullable(hm.getMetadata().getNamespace()).orElse("CLUSTER"),
        hm.getMetadata().getName()
      );
      System.out.println("Listing Events:");
      kc.events().v1().events().list().getItems().forEach(print);
      System.out.println("Listing Controller Revisions:");
      kc.apps().controllerRevisions().list().getItems().forEach(print);
      System.out.println("Listing Flow Schemas:");
      kc.flowControl().v1beta1().flowSchema().list().getItems().forEach(print);
      System.out.println("Listing Priority Level Configurations:");
      kc.flowControl().v1beta1().priorityLevelConfigurations().list().getItems().forEach(print);
      System.out.println("Listing CSI Drivers:");
      kc.storage().csiDrivers().list().getItems().forEach(print);
      System.out.println("Listing CSI Nodes:");
      kc.storage().csiNodes().list().getItems().forEach(print);
      System.out.println("Listing Storage Classes:");
      kc.storage().storageClasses().list().getItems().forEach(print);
      System.out.println("Listing Volume Attachments:");
      kc.storage().volumeAttachments().list().getItems().forEach(print);
      System.out.println("Listing CSI Storage Capacity:");
      kc.storage().csiStorageCapacities().list().getItems().forEach(print);
    }
  }
}