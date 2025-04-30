package Q3;

import java.time.LocalDate;
import java.util.*;

// Abstract Class: InsurancePolicy
abstract class InsurancePolicy {
    String policyId;
    Vehicle vehicle;
    Person policyHolder;
    double coverageAmount;
    double premiumAmount;
    LocalDate policyStartDate;
    LocalDate policyEndDate;

    abstract void calculatePremium();
    abstract void processClaim(double claimAmount);
    abstract void generatePolicyReport();
    abstract void validatePolicy();
}

// Concrete Classes
class ComprehensivePolicy extends InsurancePolicy {
    @Override
    void calculatePremium() {
        int vehicleAge = LocalDate.now().getYear() - vehicle.getVehicleYear();
        premiumAmount = coverageAmount * 0.05 + vehicleAge * 50;
        System.out.println("Premium calculated: $" + premiumAmount);
    }

    @Override
    void processClaim(double claimAmount) {
        if (claimAmount <= coverageAmount) {
            System.out.println("Claim processed successfully for amount: $" + claimAmount);
        } else {
            System.out.println("Claim exceeds coverage amount. Processing failed.");
        }
    }

    @Override
    void generatePolicyReport() {
        System.out.println("Policy Report: Comprehensive Policy");
        System.out.println("Policy ID: " + policyId);
        System.out.println("Coverage Amount: $" + coverageAmount);
        System.out.println("Premium Amount: $" + premiumAmount);
    }

    @Override
    void validatePolicy() {
        if (vehicle.getVehicleType().equalsIgnoreCase("car") && vehicle.getVehicleYear() >= 2000) {
            System.out.println("Policy validated successfully.");
        } else {
            System.out.println("Invalid vehicle type or model year.");
        }
    }
}

// Encapsulation Classes
class Vehicle {
    private String vehicleId;
    private String vehicleMake;
    private String vehicleModel;
    private int vehicleYear;
    private String vehicleType;

    public Vehicle(String vehicleId, String vehicleMake, String vehicleModel, int vehicleYear, String vehicleType) {
        this.vehicleId = vehicleId;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleYear = vehicleYear;
        this.vehicleType = vehicleType;
    }

    public int getVehicleYear() {
        return vehicleYear;
    }

    public String getVehicleType() {
        return vehicleType;
    }
}

class Person {
    private String personId;
    private String fullName;
    private LocalDate dob;
    private String email;
    private String phone;

    public Person(String personId, String fullName, LocalDate dob, String email, String phone) {
        this.personId = personId;
        this.fullName = fullName;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }
}

class Claim {
    private String claimId;
    private double claimAmount;
    private LocalDate claimDate;
    private String claimStatus;

    public Claim(String claimId, double claimAmount, LocalDate claimDate) {
        this.claimId = claimId;
        this.claimAmount = claimAmount;
        this.claimDate = claimDate;
        this.claimStatus = "Pending";
    }

    public void validateClaim(double coverageAmount) {
        if (claimAmount <= coverageAmount) {
            claimStatus = "Approved";
            System.out.println("Claim approved for amount: $" + claimAmount);
        } else {
            claimStatus = "Rejected";
            System.out.println("Claim rejected. Amount exceeds coverage limit.");
        }
    }
}

// Q1.Main Class
public class MotorVehicleInsuranceSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Capturing Vehicle Information
        System.out.println("Enter vehicle ID:");
        String vehicleId = scanner.nextLine();

        System.out.println("Enter vehicle make:");
        String vehicleMake = scanner.nextLine();

        System.out.println("Enter vehicle model:");
        String vehicleModel = scanner.nextLine();

        System.out.println("Enter vehicle year:");
        int vehicleYear = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter vehicle type (e.g., car, truck):");
        String vehicleType = scanner.nextLine();

        Vehicle vehicle = new Vehicle(vehicleId, vehicleMake, vehicleModel, vehicleYear, vehicleType);

        // Capturing Person Information
        System.out.println("\nEnter policyholder name:");
        String fullName = scanner.nextLine();

        System.out.println("Enter policyholder email:");
        String email = scanner.nextLine();

        System.out.println("Enter policyholder phone:");
        String phone = scanner.nextLine();

        System.out.println("Enter policyholder ID:");
        String personId = scanner.nextLine();

        Person policyHolder = new Person(personId, fullName, LocalDate.now(), email, phone);

        // Creating a Policy
        ComprehensivePolicy policy = new ComprehensivePolicy();
        policy.policyId = "P001";
        policy.vehicle = vehicle;
        policy.policyHolder = policyHolder;
        policy.coverageAmount = 20000;
        policy.policyStartDate = LocalDate.now();
        policy.policyEndDate = LocalDate.now().plusYears(1);

        System.out.println("\nProcessing Comprehensive Policy...");
        policy.calculatePremium();
        policy.validatePolicy();
        policy.generatePolicyReport();

        // Claim Processing
        System.out.println("\nEnter claim amount:");
        double claimAmount = scanner.nextDouble();

        policy.processClaim(claimAmount);

        scanner.close();
    }
}
