package ru.xyz.vir.host;

import ru.xyz.Vir_USCore;
import ru.xyz.function.Function;
import ru.xyz.perm.manager.Perm;
import ru.xyz.perm.perms.NetworkPerm;
import ru.xyz.vir.exception.Vir_usException;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class NetworkCapture implements Function {

    @Override
    public Perm requiredPermisionLevel() {
        return new NetworkPerm();
    }

    /**
     * Retrieves the hostname of the local machine.
     *
     * @return the hostname of the local machine
     * @throws UnknownHostException if the local host name could not be resolved into an address
     */
    public static String getHostname() throws UnknownHostException {
        if(!Vir_USCore.contains(new NetworkCapture().requiredPermisionLevel())) {
            throw new Vir_usException("Brak wymaganych uprawnień", "Brak wymaganych uprawnień");
        }
        return InetAddress.getLocalHost().getHostName();
    }


    /**
     * Retrieves the local IP address of the machine.
     *
     * @return the local IP address of the machine
     * @throws UnknownHostException if the local host address could not be resolved into an address
     */
    public static String getLocalIP() throws UnknownHostException {
        if(!Vir_USCore.contains(new NetworkCapture().requiredPermisionLevel())) {
            throw new Vir_usException("Brak wymaganych uprawnień", "Brak wymaganych uprawnień");
        }
        return InetAddress.getLocalHost().getHostAddress();
    }

    /**
     * Retrieves a list of network interfaces and their associated IP addresses.
     *
     * @return a list of network interfaces and their IP addresses
     * @throws Vir_usException if there is an error while getting network interfaces
     */
    public static List<String> getNetworkInterfaces() {
        if(!Vir_USCore.contains(new NetworkCapture().requiredPermisionLevel())) {
            throw new Vir_usException("Brak wymaganych uprawnień", "Brak wymaganych uprawnień");
        }
        List<String> interfaces = new ArrayList<>();
        Enumeration<NetworkInterface> networkInterfaces = null;
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            throw new Vir_usException("Error while getting network interfaces", e.getMessage());
        }
        while (true) {
            assert networkInterfaces != null;
            if (!networkInterfaces.hasMoreElements()) break;
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            interfaces.add(networkInterface.getDisplayName());
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                interfaces.add(inetAddress.getHostAddress());
            }
        }
        return interfaces;
    }
}