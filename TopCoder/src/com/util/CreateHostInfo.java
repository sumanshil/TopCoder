package com.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CreateHostInfo {

    private static final int MAX_NUMBER_OF_ELEMENTS = 1000;

    private static final int HOSTS_MOID_START = 252;
    private static final int HOSTS_ID_START = 283;
    private static final int VMKNIC_ID_START = 300;


    public static final String str = "INSERT INTO domain_object (dtype, objectid, cascade_event_to_parent, cascade_parent_delete, description, name, objecttype, path, revision, relationship_revision, inheritance_allowed, system_resource, vc_parent, vcref, device_key, label, mac_address, port_key, portgroup_id, portgroup_key, vnic_uuid, ssl_thumbprint, email, fullname, is_enabled, is_local, is_group, is_cli, password_hash, user_id, host_id, instance_uuid, resource_pool_id, template, uuid, config_path_at_host, vm_type_string, tools_running_status, guest_host_name, guest_full_name, product_forwarding_class, product_name, product_vendor, product_version, permission, folder_type, use_parent_child, dvs_id, uplink_portgroup, vlan_id, host_folder_id, network_folder_id, vm_folder_id, extensions, is_extensions_included, last_modified_after, last_modified_before, scan_all_files, size_less_than_bytes, parent, access_control_entry_id, access_control_entry_resource_id, ip_address, operator, key, criteria, value, client_handle, opaque_network_id, opaque_network_name, opaque_network_type, is_valid, precedence, policy_binding_revision, is_precanned, is_referenced, criteria_operator, lacp_api_version, num_cpu_cores, num_cpu_packages, hardware_uuid, host_version, powered_on, vm_path_name, common_name, pem_encoding, private_key, trust_object_type, algorithm, key_size, subject, is_deployed, execution_order_category, execution_order, is_action_enabled, is_inheritable, weightage, action_type, is_action_enforced, runtime_connection_state, runtime_power_state, runtime_standby_mode, runtime_in_maintenance_mode, uplink_teaming_policy, is_universal, universal_revision, published_ip_address, spoofguard_enabled, approved_by, approved_on, operation_mode, published_by, published_on, review_required, context_id, namespace_type, scope_id, datacenter_id) VALUES ('VimHostSystem', 'host-$', true, false, NULL, '10.192.159.14', 'HostSystem', '/group-d1/group-d2/datacenter-3/group-h5/domain-c8/host-$', 25, 0, NULL, false, 'domain-c8', 'host-$', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '09:7A:63:7F:66:6A:6A:E6:DF:AB:9D:26:60:37:14:00:D3:10:82:4A', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'domain-c8', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, true, NULL, NULL, 4, 4, '42345cf5-7a43-80f6-cdcf-67484c25596c', '6.5.0', false, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, false, NULL, NULL, false, false, NULL, NULL, false, 'connected', 'poweredOn', 'none', false, NULL, false, 0, NULL, false, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);\n";
    public static final String str1 = "INSERT INTO xvs_host_info (id, host_id, status, cluster_id) VALUES (@, 'host-$', 4, 'domain-c18');\n";

    public static void create() throws IOException {
        File file = new File("/Users/sshil/Create.sql");
        if (file.exists()) {
            file.delete();
        }
        file = new File("/Users/sshil/Create.sql");
        PrintWriter printWriter = new PrintWriter(new FileWriter(file));
        insertDomainObject(printWriter);
        insertXvsHostVmknicInfo(printWriter);
        createVmknicInfo(printWriter);
        printWriter.close();
    }

    public static void delete() throws IOException {
        File file = new File("/Users/sshil/Delete.sql");
        if (file.exists()) {
            file.delete();
        }
        file = new File("/Users/sshil/Delete.sql");
        PrintWriter printWriter = new PrintWriter(new FileWriter(file));
        deleteDomainObject(printWriter);
        deleteXvsHostInfo(printWriter);
        deleteXvsVmknicInfo(printWriter);
        printWriter.close();
    }

    private static void deleteDomainObject(PrintWriter printWriter){
        for (int i = HOSTS_MOID_START; i < maxHostIds(); i++){
            String str = "DELETE FROM domain_object WHERE objectid='host-"+i+"';\n";
            printWriter.write(str);
        }
    }

    private static void deleteXvsHostInfo(PrintWriter printWriter){
        for (int i = HOSTS_ID_START; i < maxHostIds(); i++){
            String str = "DELETE FROM xvs_host_info WHERE id="+i+";\n";
            printWriter.write(str);
        }
    }

    private static void deleteXvsVmknicInfo(PrintWriter printWriter){
        for (int i = VMKNIC_ID_START; i < maxVmknic(); i++){
            String str = "DELETE FROM xvs_vmknic_info WHERE id="+i+";\n";
            printWriter.write(str);
        }
    }


    private static void createVmknicInfo(PrintWriter printWriter){
        for (int i = VMKNIC_ID_START, j = HOSTS_ID_START; i < maxVmknic(); i++, j++ ){
            String str = "INSERT INTO xvs_vmknic_info (id, status, vmknic_device_id, host_id, allocated_ip_id, missing_on_host)" +
                    " VALUES ("+i+", 0, 'vmk1', "+j+", -1, false);\n";
            printWriter.write(str);
        }
    }

    private static int maxVmknic() {
        return VMKNIC_ID_START + MAX_NUMBER_OF_ELEMENTS;
    }


    public static void insertDomainObject(PrintWriter printWriter) throws IOException {
        for (int i = HOSTS_MOID_START; i < maxHostMoIds(); i++){
            String replaced = str.replaceAll("\\$",i+"");
            printWriter.write(replaced);
        }
    }

    private static int maxHostIds() {
        return HOSTS_ID_START+MAX_NUMBER_OF_ELEMENTS;
    }

    private static int maxHostMoIds() {
            return HOSTS_MOID_START+MAX_NUMBER_OF_ELEMENTS;
    }

    public static void insertXvsHostVmknicInfo(PrintWriter printWriter){
        for (int i = HOSTS_ID_START, j = HOSTS_MOID_START; i < maxHostIds(); i++, j++) {
            String str = "INSERT INTO xvs_host_info (id, host_id, status, cluster_id) VALUES ("+i+", 'host-"+j+"', 4, 'domain-c18');\n";
            printWriter.write(str);
        }
    }

    /*
    public static void delete() throws IOException {
        String delete = "delete from domain_object where objectid='$';";
        File file = new File("/Users/sshil/HostDelete.txt");
        PrintWriter printWriter = new PrintWriter(new FileWriter(file));
        for ( int i = 252; i <=1000 ; i++){
            String replaced = delete.replaceAll("\\$",i+"");
            printWriter.write(replaced+"\n");
        }
        printWriter.close();

    }

    */
    public static void main(String[] args) throws IOException {
        /*File file = new File("/Users/sshil/HostFile2.txt");
        PrintWriter printWriter = new PrintWriter(new FileWriter(file));
        int index = 283;
        for ( int i = 252; i <=1000 ; i++){
            String replaced = str1.replaceAll("\\$",i+"");
            String replaced1 = replaced.replaceAll("@",(index++)+"");
            printWriter.write(replaced1+"\n");
        }
        printWriter.close();
        */
        create();
        delete();
        //create();
    }
}
