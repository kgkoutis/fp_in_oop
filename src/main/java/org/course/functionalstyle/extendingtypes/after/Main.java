package org.course.functionalstyle.extendingtypes.after;

/**
 * OOP approach: extend the type with the Decorator/Adapter/Proxy pattern.
 * Pros: a) the method can be inherited for inherited subclasses and b) you don't have to remember a class that extends your type, just hit dot and see
 * what methods are available from intellisense.
 * Cons: the logic is "baked in" the type.
 */
public class Main {
        public static void main(String[] args) {
            WindExtender w = new WindExtender(new Wind(1));
            printSpeed(w);
            printSpeed2(w);
            printSpeed3(w);
        }

        private static void printSpeed(WindExtender w) {
            needWind(w);
            double kmsPerhr = w.getSpeedInKilometersPerHour(); // don't forget this!
            System.out.println("Speed in kilometers Per Hour: " + kmsPerhr);
        }

        private static void printSpeed2(WindExtender w) {
            needWind(w);
            double kmsPerhr = w.getSpeedInKilometersPerHour(); // don't forget this!
            System.out.println("Speed in kilometers Per Hour: " + kmsPerhr);
        }

        private static void printSpeed3(WindExtender w) {
            needWind(w);
            double kmsPerhr = w.getSpeedInKilometersPerHour(); // don't forget this!
            System.out.println("Speed in kilometers Per Hour: " + kmsPerhr);
        }

        private static void needWind(Wind w) {
            //.... logic with wind...//
            /* no operation */
        }
}
