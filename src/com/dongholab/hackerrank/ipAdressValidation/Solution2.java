package com.dongholab.hackerrank.ipAdressValidation;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Solution2 {
    private static String regexIPv4 = "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$";
    private static final int IPV6_MAX_HEX_GROUPS = 8;
    private static final int IPV6_MAX_HEX_DIGITS_PER_GROUP = 4;
    private static final int MAX_UNSIGNED_SHORT = 0xffff;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String ipText = br.readLine();
            if (isValidInet4Address(ipText)) {
                System.out.println("IPv4");
            } else if (isValidInet6Address(ipText)) {
                System.out.println("IPv6");
            } else {
                System.out.println("Neither");
            }
        }
    }

    public static boolean isValidInet4Address(String ipv4Address) {
        Pattern ipv4Pattern = Pattern.compile(regexIPv4);
        return ipv4Pattern.matcher(ipv4Address).matches();
    }

    public static boolean isValidInet6Address(String ipv6Address) {
        String[] parts;
        parts = ipv6Address.split("/", -1);
        if (parts.length > 2) {
            return false;
        }
        if (parts.length == 2) {
            if (parts[1].matches("\\d{1,3}")) {
                int bits = Integer.parseInt(parts[1]);
                if (bits < 0 || bits > 128) {
                    return false;
                }
            } else {
                return false;
            }
        }

        parts = parts[0].split("%", -1);
        if (parts.length > 2) {
            return false;
        } else if (parts.length == 2) {
            if (!parts[1].matches("[^\\s/%]+")) {
                return false;
            }
        }
        ipv6Address = parts[0];
        boolean containsCompressedZeroes = ipv6Address.contains("::");
        if (containsCompressedZeroes && (ipv6Address.indexOf("::") != ipv6Address.lastIndexOf("::"))) {
            return false;
        }
        if ((ipv6Address.startsWith(":") && !ipv6Address.startsWith("::"))
                || (ipv6Address.endsWith(":") && !ipv6Address.endsWith("::"))) {
            return false;
        }
        String[] octets = ipv6Address.split(":");
        if (containsCompressedZeroes) {
            List<String> octetList = new ArrayList<>(Arrays.asList(octets));
            if (ipv6Address.endsWith("::")) {
                octetList.add("");
            } else if (ipv6Address.startsWith("::") && !octetList.isEmpty()) {
                octetList.remove(0);
            }
            octets = octetList.toArray(new String[octetList.size()]);
        }
        if (octets.length > IPV6_MAX_HEX_GROUPS) {
            return false;
        }
        int validOctets = 0;
        int emptyOctets = 0;
        for (int index = 0; index < octets.length; index++) {
            String octet = octets[index];
            if (octet.length() == 0) {
                emptyOctets++;
                if (emptyOctets > 1) {
                    return false;
                }
            } else {
                emptyOctets = 0;
                if (index == octets.length - 1 && octet.contains(".")) {
                    if (!isValidInet4Address(octet)) {
                        return false;
                    }
                    validOctets += 2;
                    continue;
                }
                if (octet.length() > IPV6_MAX_HEX_DIGITS_PER_GROUP) {
                    return false;
                }
                int octetInt = 0;
                try {
                    octetInt = Integer.parseInt(octet, 16);
                } catch (NumberFormatException e) {
                    return false;
                }
                if (octetInt < 0 || octetInt > MAX_UNSIGNED_SHORT) {
                    return false;
                }
            }
            validOctets++;
        }

        if (validOctets > IPV6_MAX_HEX_GROUPS || (validOctets < IPV6_MAX_HEX_GROUPS && !containsCompressedZeroes)) {
            return false;
        }
        return true;
    }
}
