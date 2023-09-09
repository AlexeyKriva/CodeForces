import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double power = scanner.nextDouble();
        double allPower = 0;

        double[] powerGadget = new double[n];
        double[] energy = new double[n];

        for (int i = 0; i < n; ++i) {
            powerGadget[i] = scanner.nextDouble();
            energy[i] = scanner.nextDouble();
            allPower += powerGadget[i];
        }

        if (power >= allPower) {
            System.out.println(-1);
        }
        else {
            double left = 0;
            double right = 100000000000000000d;

            for (int i = 0; i < 250; i++) {
                double middle = (left + right) / 2;
                double energyTime = 0;
                for (int j = 0; j < n; j++) {
                    if (middle * powerGadget[j] > energy[j]) {
                        energyTime += (middle * powerGadget[j] - energy[j]) / power;
                    }
                }
                if (energyTime > middle) {
                    right = middle;
                } else {
                    left = middle;
                }
            }

            System.out.println(String.format("%.5f", left));
        }
    }
}
