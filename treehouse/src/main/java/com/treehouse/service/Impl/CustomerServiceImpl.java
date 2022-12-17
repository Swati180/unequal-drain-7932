package com.treehouse.service.Impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.treehouse.Repo.*;
import com.treehouse.model.*;
import com.treehouse.model.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treehouse.exception.CustomerException;
import com.treehouse.service.CustomerService;

import net.bytebuddy.utility.RandomString;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerLoginRepo customerLoginRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private AdminLoginRepo adminLoginRepo;
    @Autowired
    private PlantRepo plantRepo;
    @Autowired
    private BucketRepo bucketRepo;
    @Autowired
    private SeedRepo seedRepo;
    @Autowired
    private PlantDtoRepo plantDtoRepo;
    @Autowired
    private SeedsDtoRepo seedsDtoRepo;
    @Autowired
    private PlanterRepo planterRepo;
    @Autowired
    private PlanterDtoRepo planterDtoRepo;
    @Override
    public Customer registerCustomer(CustomerDTO customer) throws CustomerException {

        Customer customer1 = new Customer();
        customer1.setEmail(customer.getEmail());
        customer1.setMobile(customer.getMobile());
        customer1.setFirstname(customer.getFirstname());
        customer1.setLastName(customer.getLastName());
        customer1.setPassword(customer.getPassword());

        return customerRepo.save(customer1);
    }

    @Override
    public Customer updateCustomer(CustomerDTO customerDTO, Integer customerId) throws CustomerException {



        Optional<Customer> customerOptional = customerRepo.findById(customerId);
        if(customerOptional.isPresent()){
            Customer customer2 = new Customer();
            customer2.setCustomerId(customerId);
            customer2.setPassword(customerDTO.getPassword());
            customer2.setLastName(customerDTO.getLastName());
            customer2.setFirstname(customerDTO.getFirstname());
            customer2.setEmail(customerDTO.getEmail());
            customer2.setBucket(customerOptional.get().getBucket());

            return customerRepo.save(customer2);

        }

        throw new CustomerException("ID is not Valid");


    }

    @Override
    public Customer deleteCustomer(Integer id) throws CustomerException {


            Optional<Customer> customerOptional = customerRepo.findById(id);

            if(customerOptional.isPresent()){
                customerRepo.delete(customerOptional.get());
                Optional<CustomerLogin> customerLogin=customerLoginRepo.findById(id);
               if(customerLogin.isPresent()){

                   customerLoginRepo.delete(customerLogin.get());
                   return customerOptional.get();
               }

                return customerOptional.get();
            }
            throw new CustomerException("Pass the valid ID");


    }

    @Override
    public List<Customer> getAllCustomer(String token) throws CustomerException {

        AdminLogin al = adminLoginRepo.findByKey(token);
        if(al!=null){
            return customerRepo.findAll();
        }
        throw new CustomerException("You are not Authorized");
    }

    @Override
    public Customer getCustomerById(Integer id) throws CustomerException {
        Optional<Customer> customerOptional = customerRepo.findById(id);
        if(customerOptional.isPresent()){
            return customerOptional.get();
        }
        throw new CustomerException("valid not found");
    }

    @Override
    public CustomerLogin loginCustomer(CustomerLoginDto customerLoginDto) throws CustomerException {

        Customer customer = customerRepo.findByEmailAndPassword(customerLoginDto.getEmail(), customerLoginDto.getPassword());
        if(customer != null){
            CustomerLogin customerLogin = new CustomerLogin();
            customerLogin.setLocalDate(LocalDate.now());
            String key = RandomString.make(6);
            customerLogin.setKey(key);
            customerLogin.setCustomerId(customer.getCustomerId());
            return customerLoginRepo.save(customerLogin);
        }
        throw new CustomerException("Details Entered Wrong");


    }

    @Override
    public CustomerLogin logoutCustomer(String key) throws CustomerException {
        CustomerLogin customerLogin=customerLoginRepo.findByKey(key);
        if(customerLogin!=null){
            customerLoginRepo.delete(customerLogin);
            return customerLogin;
        }
        throw new CustomerException("please enter Valid key");
    }

    @Override
    public Bucket addPlantToBucket(Integer plantId, String key) throws CustomerException {

        CustomerLogin customerLogin =  this.checkPermission(key);
        Optional<Plant> optionalPlant = plantRepo.findById(plantId);
        if(customerLogin != null && optionalPlant.isPresent()){
            Optional<Customer> optionalCustomer1 = customerRepo.findById(customerLogin.getCustomerId());
            Bucket bucket = optionalCustomer1.get().getBucket();
            Plant plants= optionalPlant.get();
            PlantDto plant=new PlantDto();
            plant.setPlantId(plants.getPlantId());
            plant.setPlantCost(plants.getPlantCost());
//            plant.setPlantQuantity(plants.getPlantQuantity());
            plant.setPlantHeight(plants.getPlantHeight());
            plant.setPlantSpread(plants.getPlantSpread());
            plant.setBloomTime(plants.getBloomTime());
            plant.setCommonName(plants.getCommonName());
            plant.setDifficultyLevel(plants.getDifficultyLevel());
            plant.setMedicinalOrCulinaryUse(plants.getMedicinalOrCulinaryUse());
            plant.setPlantsStock(plants.getPlantsStock());
            plant.setTypeOfPlant(plants.getTypeOfPlant());

            boolean flag=false;
            int count=0;
            if(bucket != null){
                for(PlantDto i:bucket.getPlants()){
                    if(i.getPlantId()==plantId){
                        flag=true;
                        count+=1;

                    }
                }
                if(flag){
                    Optional<PlantDto> plantDtoOptional=plantDtoRepo.findById(plantId);
                    plant=plantDtoOptional.get();
                    count+=plant.getPlantQuantity();
                    plant.setPlantCost(plant.getPlantCost()+plants.getPlantCost());
                    plant.setPlantQuantity(count);
                    if(bucket.getPlantQuantity()==null){
                        bucket.setPlantQuantity(1);
                    }
                    else{
                        bucket.setPlantQuantity(bucket.getPlantQuantity()+1);
                    }
                    plantDtoRepo.save(plant);
                    if(bucket.getPlantPrice()!=null){
                        bucket.setPlantPrice(bucket.getPlantPrice()+plants.getPlantCost());

                    }
                    else{
                        bucket.setPlantPrice(plants.getPlantCost());
                    }
                    if(bucket.getTotalItems()!=null){
                        bucket.setTotalItems(bucket.getTotalItems()+1);
                    }
                    else{
                        bucket.setTotalItems(1);
                    }
                    if(bucket.getTotalPrice()!=null){
                        bucket.setTotalPrice(bucket.getTotalPrice()+plants.getPlantCost());
                    }
                    else{
                        bucket.setTotalPrice(plants.getPlantCost());
                    }
                    optionalCustomer1.get().setBucket(bucket);
                    return customerRepo.save(optionalCustomer1.get()).getBucket();
                }
                else{
                    plant.setPlantQuantity(1);
                    plantDtoRepo.save(plant);
                    bucket.getPlants().add(plant);
                    if(bucket.getPlantPrice()!=null){
                        bucket.setPlantPrice(bucket.getPlantPrice()+plant.getPlantCost());
                    }
                    else{
                        bucket.setPlantPrice(plant.getPlantCost());
                    }
                    if(bucket.getPlantQuantity()!=null){
                        bucket.setPlantQuantity(bucket.getPlantQuantity()+1);

                    }
                    else{
                        bucket.setPlantQuantity(1);

                    }
                    if(bucket.getTotalItems()!=null){
                        bucket.setTotalItems(bucket.getTotalItems()+1);

                    }
                    else{
                        bucket.setTotalItems(1);

                    }
                    if(bucket.getTotalPrice()!=null){
                        bucket.setTotalPrice(bucket.getTotalPrice()+plant.getPlantCost());
                    }
                    else{
                        bucket.setTotalPrice(plant.getPlantCost());
                    }
                    optionalCustomer1.get().setBucket(bucket);
                    return customerRepo.save(optionalCustomer1.get()).getBucket();


                }


            }else{
                plant.setPlantQuantity(1);
                plantDtoRepo.save(plant);
                Bucket bucket1 = new Bucket();
                bucket1.getPlants().add(plant);
                bucket1.setPlantQuantity(1);
                bucket1.setTotalItems(1);
                bucket1.setTotalPrice(plant.getPlantCost());
                bucket1.setPlantPrice(plant.getPlantCost());
                optionalCustomer1.get().setBucket(bucket1);
                return customerRepo.save(optionalCustomer1.get()).getBucket();


            }
        }
        throw new CustomerException("Either Your Login Key or Plant detail is not correct");
    }

    @Override
    public Bucket addPlanterToBucket(Integer planterId, String key) throws CustomerException {
        CustomerLogin customerLogin =  this.checkPermission(key);
        Optional<Planter> optionalPlanter = planterRepo.findById(planterId);
        if(customerLogin != null && optionalPlanter.isPresent()){
            Optional<Customer> optionalCustomer1 = customerRepo.findById(customerLogin.getCustomerId());
            Bucket bucket = optionalCustomer1.get().getBucket();
            Planter plants= optionalPlanter.get();
            PlanterDto plant=new PlanterDto();
            plant.setPlanterId(plants.getPlanterId());
            plant.setPlanterCost(plants.getPlanterCost());
//            plant.setPlantQuantity(plants.getPlantQuantity());
            plant.setPlanterColor(plants.getPlanterColor());
            plant.setPlanterCapacity(plants.getPlanterCapacity());
            plant.setPlanterHeight(plants.getPlanterHeight());
            plant.setPlanterShape(plants.getPlanterShape());
            plant.setPlanterStock(plants.getPlanterStock());
            plant.setDrainHoles(plants.getDrainHoles());

            boolean flag=false;
            int count=0;
            if(bucket != null){
                for(PlanterDto i:bucket.getPlanters()){
                    if(i.getPlanterId()==planterId){
                        flag=true;
                        count+=1;

                    }
                }
                if(flag){
                    Optional<PlanterDto> plantDtoOptional=planterDtoRepo.findById(planterId);
                    plant=plantDtoOptional.get();
                    count+=plant.getPlanterQuantity();

                    plant.setPlanterCost(plant.getPlanterCost()+plants.getPlanterCost());
                    plant.setPlanterQuantity(count);
                    planterDtoRepo.save(plant);
                    if(bucket.getPlanterQuantity()!=null){
                        System.out.println(bucket.getPlanterQuantity());
                        bucket.setPlanterQuantity(bucket.getPlanterQuantity()+1);
                    }
                    else{
                        bucket.setPlanterQuantity(1);
                    }
                    if(bucket.getPlanterPrice()!=null){
                        bucket.setPlanterPrice(bucket.getPlanterPrice()+plants.getPlanterCost());

                    }
                    else{
                        bucket.setPlanterPrice(plants.getPlanterCost());

                    }
                    if(bucket.getTotalItems()!=null){
                        bucket.setTotalItems(bucket.getTotalItems()+1);
                    }
                    else{
                        bucket.setTotalItems(1);
                    }
                    if(bucket.getTotalPrice()!=null){
                        bucket.setTotalPrice(bucket.getTotalPrice()+plants.getPlanterCost());

                    }
                    else{
                        bucket.setTotalPrice(plants.getPlanterCost());
                    }
                    optionalCustomer1.get().setBucket(bucket);
                    return customerRepo.save(optionalCustomer1.get()).getBucket();
                }
                else{
                    plant.setPlanterQuantity(1);
                    planterDtoRepo.save(plant);
                    bucket.getPlanters().add(plant);
                    if(bucket.getPlanterQuantity()!=null){
                        bucket.setPlantQuantity(bucket.getPlanterQuantity()+1);
                    }else{
                        bucket.setPlanterQuantity(1);
                    }
                    if(bucket.getPlanterPrice()!=null){
                        bucket.setPlanterPrice(bucket.getPlanterPrice()+plants.getPlanterCost());
                    }
                    else{
                        bucket.setPlanterPrice(plants.getPlanterCost());
                    }
                    if(bucket.getTotalItems()!=null){
                        bucket.setTotalItems(bucket.getTotalItems()+1);
                    }else {
                        bucket.setTotalItems(1);
                    }
                    if(bucket.getTotalPrice()!=null){
                        bucket.setTotalPrice(bucket.getTotalPrice()+plants.getPlanterCost());
                    }
                    else{
                        bucket.setTotalPrice(plants.getPlanterCost());
                    }
                    optionalCustomer1.get().setBucket(bucket);
                    return customerRepo.save(optionalCustomer1.get()).getBucket();
                }





            }else{
                plant.setPlanterQuantity(1);
                planterDtoRepo.save(plant);
                Bucket bucket1 = new Bucket();
                bucket1.getPlanters().add(plant);
                bucket1.setPlanterQuantity(1);
                bucket1.setTotalItems(1);
                bucket1.setTotalPrice(plant.getPlanterCost());
                bucket1.setPlanterPrice(plant.getPlanterCost());
                optionalCustomer1.get().setBucket(bucket1);
                return customerRepo.save(optionalCustomer1.get()).getBucket();


            }
        }
        throw new CustomerException("Either Your Login Key or Plant detail is not correct");

    }

    @Override
    public Bucket addSeedsToBucket(Integer seedsId, String key) throws CustomerException {
        CustomerLogin customerLogin =  this.checkPermission(key);
        Optional<Seeds> optionalSeeds = seedRepo.findById(seedsId);
        if(customerLogin != null && optionalSeeds.isPresent()){
            Optional<Customer> optionalCustomer1 = customerRepo.findById(customerLogin.getCustomerId());
            Bucket bucket = optionalCustomer1.get().getBucket();
            Seeds seed= optionalSeeds.get();
            SeedsDto seeds = new SeedsDto();
            seeds.setSeedsId(seed.getSeedsId());
            seeds.setSeedsStock(seed.getSeedsStock());
            seeds.setSeedsDescription(seed.getSeedsDescription());
            seeds.setBloomTime(seed.getBloomTime());
            seeds.setSeedsPerPacket(seed.getSeedsPerPacket());
            seeds.setTypeOfSeeds(seed.getTypeOfSeeds());
            seeds.setSeedsCost(seed.getSeedsCost());
            seeds.setCommonName(seed.getCommonName());
            seeds.setDifficultyLevel(seed.getTemperature());
            seeds.setWatering(seed.getWatering());
            seeds.setTemperature(seed.getTemperature());

            boolean flag=false;
            if(bucket != null){
                for(SeedsDto i:bucket.getSeeds()){
                    if(i.getSeedsId()==seedsId){
                        flag=true;
                    }
                }
                if(flag){
                    Optional<SeedsDto> seeds1 = seedsDtoRepo.findById(seedsId);
                    seeds = seeds1.get();
                    seeds.setSeedsQuantity(seeds.getSeedsQuantity()+1);
                    seeds.setSeedsCost(seeds.getSeedsCost()+seed.getSeedsCost());
                    seedsDtoRepo.save(seeds);
                    if(bucket.getSeedsQuantity()==null){
                        bucket.setSeedsQuantity(1);
                    }
                    else{
                        bucket.setSeedsQuantity(bucket.getSeedsQuantity()+1);
                    }
                    if(bucket.getTotalItems()!=null){
                        bucket.setTotalItems(bucket.getTotalItems()+1);

                    }
                    else{
                        bucket.setTotalItems(1);
                    }
                    if(bucket.getTotalPrice()!=null){
                        bucket.setTotalPrice(bucket.getTotalPrice()+seed.getSeedsCost());
                    }
                    else{
                        bucket.setTotalPrice(seed.getSeedsCost());
                    }
                    if(bucket.getSeedsPrice()!=null){
                        bucket.setSeedsPrice(bucket.getSeedsPrice()+seed.getSeedsCost());
                    }
                    else{
                        bucket.setSeedsPrice(seed.getSeedsCost());

                    }
                    optionalCustomer1.get().setBucket(bucket);
                    return customerRepo.save(optionalCustomer1.get()).getBucket();
                }
                else{
                    seeds.setSeedsQuantity(1);
                    seedsDtoRepo.save(seeds);
                    bucket.getSeeds().add(seeds);
                    if(bucket.getSeedsQuantity()!=null){
                        bucket.setSeedsQuantity(bucket.getSeedsQuantity()+1);

                    }else {
                        bucket.setSeedsQuantity(1);
                    }
                    if(bucket.getTotalItems()!=null){
                           bucket.setTotalItems(bucket.getTotalItems()+1);
                    }
                    else{
                            bucket.setTotalItems(1);
                    }
                    if(bucket.getSeedsPrice()!=null){
                           bucket.setSeedsPrice(bucket.getSeedsPrice()+seed.getSeedsCost());
                    }
                        bucket.setSeedsPrice(seed.getSeedsCost());
                    }
                if(bucket.getTotalPrice()!=null){
                    bucket.setTotalPrice(bucket.getTotalPrice()+seed.getSeedsCost());
                }
                else{
                    bucket.setTotalPrice(seed.getSeedsCost());
                }
                    optionalCustomer1.get().setBucket(bucket);
                    return customerRepo.save(optionalCustomer1.get()).getBucket();




            }else{
                seeds.setSeedsQuantity(1);
                seedsDtoRepo.save(seeds);
                Bucket bucket1 = new Bucket();
                bucket1.getSeeds().add(seeds);
                bucket1.setSeedsQuantity(1);
                bucket1.setTotalItems(1);
                bucket1.setTotalPrice(seeds.getSeedsCost());

                bucket1.setSeedsPrice(seeds.getSeedsCost());

                optionalCustomer1.get().setBucket(bucket1);
                System.out.println(bucket1);
                return customerRepo.save(optionalCustomer1.get()).getBucket();


            }
        }
        throw new CustomerException("Either Your Login Key or Seeds detail is not correct");

    }

    @Override
    public Bucket decreaseQuantityOfSeeds(Integer seedsID, String key) throws CustomerException {
        CustomerLogin customerLogin = this.checkPermission(key);
        Optional<Seeds> optionalSeeds = seedRepo.findById(seedsID);
        Seeds seeds = optionalSeeds.get();
        if (customerLogin != null && optionalSeeds.isPresent()) {
            Optional<Customer> optionalCustomer1 = customerRepo.findById(customerLogin.getCustomerId());
            Bucket bucket = optionalCustomer1.get().getBucket();
            boolean flag=false;
            int count=0;
            int check=0;
            if (bucket != null) {
                if (bucket.getSeedsQuantity() > 0) {
//                    System.out.println(bucket+" bucket===============================");

                    List<SeedsDto> list=bucket.getSeeds();
                    for(SeedsDto i:list){
                        if(i.getSeedsId()==seedsID){
                            bucket.setSeedsQuantity(bucket.getSeedsQuantity() - 1);
                            bucket.setSeedsPrice(bucket.getSeedsPrice() - seeds.getSeedsCost());
                            bucket.setTotalPrice(bucket.getTotalPrice() - seeds.getSeedsCost());
                            bucket.setTotalItems(bucket.getTotalItems() - 1);
                            if(i.getSeedsQuantity()>1){
                                i.setSeedsQuantity(i.getSeedsQuantity()-1);
                                i.setSeedsCost(i.getSeedsCost()-seeds.getSeedsCost());

                            }
                            else{
                                flag=true;
                                check=count;
                            }

                        }
                        count++;

                    }
                    if(flag){
                        list.remove(check);

                    }

                    optionalCustomer1.get().setBucket(bucket);
//                        System.out.println(bucket1);
                    return customerRepo.save(optionalCustomer1.get()).getBucket();
                } else {
                    throw new CustomerException("Seeds not present");
                }
            } else {
                throw new CustomerException("Cart is Empty");
            }
        }
        throw new CustomerException("Empty");


    }

    @Override
    public Bucket decreaseQuantityOfPlant(Integer plantID, String key) throws CustomerException {
        CustomerLogin customerLogin = this.checkPermission(key);
        Optional<Plant> optionalplants = plantRepo.findById(plantID);
        Plant plants = optionalplants.get();
        if (customerLogin != null && optionalplants.isPresent()) {
            Optional<Customer> optionalCustomer1 = customerRepo.findById(customerLogin.getCustomerId());
            Bucket bucket = optionalCustomer1.get().getBucket();
            boolean flag=false;
            int count=0;
            int check=0;
            if (bucket != null) {
                if (bucket.getPlantQuantity() > 0) {


                    List<PlantDto> list=bucket.getPlants();
                    System.out.println(list.size());
                    for(PlantDto i:list){
                        System.out.println(i.getPlantId()==plantID);
                        if(i.getPlantId()==plantID){
//                            System.out.println(bucket+"================================================================== bucket===============================");
                            bucket.setPlantQuantity(bucket.getPlantQuantity() - 1);
                            bucket.setPlantPrice(bucket.getPlantPrice() - plants.getPlantCost());
                            bucket.setTotalPrice(bucket.getTotalPrice() - plants.getPlantCost());
                            bucket.setTotalItems(bucket.getTotalItems() - 1);
                            if(i.getPlantQuantity()>1){
                                i.setPlantQuantity(i.getPlantQuantity()-1);
                                i.setPlantCost(i.getPlantCost()-plants.getPlantCost());

                            }
                            else{
                                flag=true;
                                check=count;
                            }

                        }
                        count++;


                    }
                    if(flag){
                        list.remove(check);

                    }
                    optionalCustomer1.get().setBucket(bucket);
//                        System.out.println(bucket1);
                    return customerRepo.save(optionalCustomer1.get()).getBucket();
                } else {
                    throw new CustomerException("Plant not present");
                }
            } else {
                throw new CustomerException("Cart is Empty");
            }
        }
        throw new CustomerException("Empty");

    }

    @Override
    public Bucket decreaseQuantityOfPlanter(Integer planterID, String key) throws CustomerException {
        CustomerLogin customerLogin = this.checkPermission(key);
        Optional<Planter> optionalplants = planterRepo.findById(planterID);
        Planter plants = optionalplants.get();
        if (customerLogin != null && optionalplants.isPresent()) {
            Optional<Customer> optionalCustomer1 = customerRepo.findById(customerLogin.getCustomerId());
            Bucket bucket = optionalCustomer1.get().getBucket();
            boolean flag=false;
            int count=0;
            int check=0;
            if (bucket != null) {
                if (bucket.getPlanterQuantity() > 0) {


                    List<PlanterDto> list=bucket.getPlanters();
                    System.out.println(list.size());
                    for(PlanterDto i:list){
                        System.out.println(i.getPlanterId()==planterID);
                        if(i.getPlanterId()==planterID){
//                            System.out.println(bucket+"================================================================== bucket===============================");
                            bucket.setPlanterQuantity(bucket.getPlanterQuantity() - 1);
                            bucket.setPlanterPrice(bucket.getPlanterPrice() - plants.getPlanterCost());
                            bucket.setTotalPrice(bucket.getTotalPrice() - plants.getPlanterCost());
                            bucket.setTotalItems(bucket.getTotalItems() - 1);
                            if(i.getPlanterQuantity()>1){
                                i.setPlanterQuantity(i.getPlanterQuantity()-1);
                                i.setPlanterCost(i.getPlanterCost()-plants.getPlanterCost());

                            }
                            else{
                                flag=true;
                                check=count;
                            }

                        }
                        count++;


                    }
                    if(flag){
                        list.remove(check);

                    }
                    optionalCustomer1.get().setBucket(bucket);
//                        System.out.println(bucket1);
                    return customerRepo.save(optionalCustomer1.get()).getBucket();
                } else {
                    throw new CustomerException("Planter not present");
                }
            } else {
                throw new CustomerException("Cart is Empty");
            }
        }
        throw new CustomerException("Empty");
    }

    // checkPermission Method
    public CustomerLogin checkPermission(String key){
        return customerLoginRepo.findByKey(key);

    }
}
