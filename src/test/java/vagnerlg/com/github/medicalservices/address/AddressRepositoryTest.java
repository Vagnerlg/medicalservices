package vagnerlg.com.github.medicalservices.address;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vagnerlg.com.github.medicalservices.MedicalServicesApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MedicalServicesApplication.class)
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void givenAddressRepository_whenSave_thenOK() {
        Address address = new Address();
        address.setStreet("Av Paulista");
        address.setNumber("1000");
        address.setPostalCode("03077000");
        address.setDistrict("Vila Mariana");
        address.setMunicipal("São Paulo");
        address.setState("SP");

        Address saveAddress = addressRepository.save(address);

        Assert.assertNotNull(saveAddress.getId());
    }
}
