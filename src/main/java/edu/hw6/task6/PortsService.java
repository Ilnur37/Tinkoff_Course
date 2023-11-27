package edu.hw6.task6;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"MagicNumber", "MultipleStringLiterals"})
public class PortsService {
    private PortsService() {

    }

    public static final Map<Integer, String> MAP = new HashMap<>();

    static {
        MAP.put(80, "HTTP (HyperText Transfer Protocol)");
        MAP.put(21, "FTP (File Transfer Protocol)");
        MAP.put(25, "SMTP (Simple Mail Transfer Protocol)");
        MAP.put(22, "SSH (Secure Shell)");
        MAP.put(443, "HTTPS (HyperText Transfer Protocol Secure)");
        MAP.put(53, "DNS (Domain Name System)");
        MAP.put(3306, "MySQL Database");
        MAP.put(5432, "PostgreSQL Database");
        MAP.put(3389, "Remote Desktop Protocol (RDP)");
        MAP.put(27017, "MongoDB Database");
        MAP.put(1521, "Oracle Database");
        MAP.put(49152, "Windows RPC (Remote Procedure Call)");
        MAP.put(5353, "mDNS (Multicast Domain Name System)");
        MAP.put(5672, "AMQP (Advanced Message Queuing Protocol)");
        MAP.put(5355, "LLMNR (Link-Local Multicast Name Resolution)");
        MAP.put(49153, "Windows RPC (Remote Procedure Call)");
        MAP.put(23, "Telnet");
        MAP.put(110, "POP3");
        MAP.put(143, "IMAP");
        MAP.put(67, "DHCP");
        MAP.put(68, "DHCP");
        MAP.put(123, "NTP");
        MAP.put(161, "SNMP");
        MAP.put(162, "SNMP");
        MAP.put(445, "SMB");
        MAP.put(548, "AFP");
        MAP.put(137, "NetBIOS");
        MAP.put(138, "NetBIOS");
        MAP.put(139, "NetBIOS");
        MAP.put(8080, "HTTP Proxy");
        MAP.put(1080, "SOCKS");
        MAP.put(1433, "MSSQL");
        MAP.put(636, "LDAP");
        MAP.put(389, "LDAP");
        MAP.put(5722, "SMB2");
        MAP.put(500, "IKE");
        MAP.put(1701, "L2TP");
        MAP.put(1723, "PPTP");
        MAP.put(3128, "HTTPS Proxy");
        MAP.put(6667, "IRC");
        MAP.put(6697, "IRC");
        MAP.put(2049, "NFS");
    }
}
