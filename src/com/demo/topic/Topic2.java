package com.demo.topic;

import java.util.*;

class City {
    private String name;
    private String code;

    public City(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}

class MyIDNumberChecker {

    private static Map<String, City> cities = new HashMap<>();

    static {
        cities.put("A", new City("臺北市", "10"));
        cities.put("B", new City("臺中市", "11"));
        cities.put("C", new City("基隆市", "12"));
        cities.put("D", new City("臺南市", "13"));
        cities.put("E", new City("高雄市", "14"));
        cities.put("F", new City("新北市(臺北縣)", "15"));
        cities.put("G", new City("宜蘭縣", "16"));
        cities.put("H", new City("桃園市", "17"));
        cities.put("J", new City("新竹縣", "18"));
        cities.put("K", new City("苗栗縣", "19"));
        cities.put("L", new City("臺中縣", "20"));
        cities.put("M", new City("南投縣", "21"));
        cities.put("N", new City("彰化縣", "22"));
        cities.put("P", new City("雲林縣", "23"));
        cities.put("Q", new City("嘉義縣", "24"));
        cities.put("R", new City("臺南縣", "25"));
        cities.put("S", new City("高雄縣", "26"));
        cities.put("T", new City("屏東縣", "27"));
        cities.put("U", new City("花蓮縣", "28"));
        cities.put("V", new City("臺東縣", "29"));
        cities.put("X", new City("澎湖縣", "30"));
        cities.put("Y", new City("陽明山", "31"));
        cities.put("W", new City("金門縣", "32"));
        cities.put("Z", new City("連江縣", "33"));
        cities.put("I", new City("嘉義市", "34"));
        cities.put("O", new City("新竹市", "35"));
    }

    public static IDInformation check(String idNumber)  {
        boolean isValid = false;
        City city = null;

        try {
            // 檢查輸入是否正常，以及字串長度是否正確。
            if (idNumber == null || idNumber.length() != 10) {
                throw new IDInvalidException();
            }

            // 獲取第一個英文字母，以及判斷英文字母是否有對應的縣市。
            String english = idNumber.substring(0, 1);
            city = cities.get(english.toUpperCase());
            if (city == null) {
                throw new IDInvalidException();
            }

            // 將輸入的身分證字號轉換成字串陣列
            List<String> idNumbers = Arrays.asList(idNumber.split(""));

            // 從第一個英文字母獲取對應縣市的數碼進行十位數以及個位數的拆分。
            Integer firstCode = (int) city.getCode().charAt(0);
            Integer secondCode = (int) city.getCode().charAt(1);

            // 獲取最後一個數字（即檢查碼）
            Integer lastNumber = Integer.parseInt(idNumbers.get(idNumbers.size() - 1));

            int sum = firstCode + (secondCode * 9);
            for (int idx = 1; idx < idNumbers.size() - 1; idx++) {
                sum += Integer.parseInt(idNumbers.get(idx)) * (idNumbers.size() - (idx + 1));
            }

            isValid = lastNumber == (10 - (sum % 10));
        } catch (IDInvalidException | NumberFormatException ignored) { }

        return new IDInformation(city, idNumber, isValid);
    }

    public static class IDInformation {
        private City city;
        private String idNumber;
        private boolean isValid;

        public IDInformation(City city, String idNumber, boolean isValid) {
            this.city = city;
            this.idNumber = idNumber;
            this.isValid = isValid;
        }

        public City getCity() {
            return city;
        }

        public String getIdNumber() {
            return idNumber;
        }

        public boolean isValid() {
            return isValid;
        }
    }

    private static class IDInvalidException extends RuntimeException {
        public IDInvalidException() {
            super("身分證字號輸入錯誤！");
        }
    }

}

public class Topic2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入您的身分證字號：");

        String idNumber = scanner.nextLine();
        MyIDNumberChecker.IDInformation idInformation = MyIDNumberChecker.check(idNumber);
        if (idInformation.isValid()) {
            System.out.println(String.format("您的身分證字號為 %s，縣市為： %s",
                    idInformation.getIdNumber(),
                    idInformation.getCity().getName()
            ));
        } else {
            System.out.println("身分證字號輸入錯誤！");
        }

        System.out.print("檢查完畢！！！！");
    }
}
