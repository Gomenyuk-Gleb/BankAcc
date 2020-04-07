public class Main {

    public static void main(String[] args) {
        Result result = new Result();
        System.out.println("");
        System.err.println(result.allUser());
        System.out.println("");
        System.err.println(result.allCure());
        System.out.println("");
        System.err.println(result.allAcc());
        System.out.println("");
        System.err.println(result.getAccountByUserAndCurrency("5555", 555D, result.carency("UAH")));
        System.out.println("");
        System.err.println(result.transferBalans(result.number("5556"), result.number("5555"), 123D));
        System.err.println("Сумма на карте клиента в 3 валютах: ");
        System.err.println(result.convertAcc(result.number("5555")));
        System.err.println("Сумарная сумма в гривнах: ");
        System.err.println(result.convertAcc(result.number("5555")));
        System.err.println("CСумма на одном аккаунте");
        System.err.println(result.countAcc(result.userNumber("0992312132")));
        System.err.println(result.countAcc(result.userNumber("0992312132")));
        System.err.println(result.summUAH(result.countAcc(result.userNumber("0992312132"))));


    }
}
