package com.payeye.app.model;

import java.util.List;
import java.util.stream.Collectors;

public class AddressBuilder {

    public static List<Address> buildFrom(List<String> addresses, Employee employee) {
        return addresses.stream()
                .map(address -> buildFrom(address, employee))
                .collect(Collectors.toList());
    }

    public static Address buildFrom(String address, Employee employee) {
        Address addr = new Address();
        addr.setAddress(address);
        addr.setEmployee(employee);
        return addr;
    }

}
