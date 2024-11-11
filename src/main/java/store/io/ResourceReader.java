package store.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceReader {

    private Map<String, PromotionData> promotionDataMap = new HashMap<>();

    private List<ProductData> productDatas = new ArrayList<>();

    public List<ProductData> readData() {
        readPromotion();
        return readProduct();
    }

    private List<ProductData> readProduct() {
        try {
            InputStream inputStream = ResourceReader.class.getResourceAsStream("/products.md");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count == 0) {
                    count++;
                    continue;
                }
                count++;
                String[] strings = line.split(",");
                ProductData productData = parseProductData(strings);
                productDatas.add(productData);
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
        return productDatas;
    }

    private void readPromotion() {
        try {
            InputStream inputStream = ResourceReader.class.getResourceAsStream("/promotions.md");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count == 0) {
                    count++;
                    continue;
                }
                count++;

                String[] strings = line.split(",");
                PromotionData promotionData = parsePromotionData(strings);
                promotionDataMap.put(promotionData.getName(), promotionData);
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

    private ProductData parseProductData(String[] strings) {
        String name = strings[0];
        int price = Integer.parseInt(strings[1]);
        int quantity = Integer.parseInt(strings[2]);

        PromotionData promotionData = null;
        if (!strings[3].equals("null")) {
            promotionData = promotionDataMap.get(strings[3]);
        }
        return new ProductData(name, price, quantity, promotionData);
    }

    private PromotionData parsePromotionData(String[] strings) {
        String name = strings[0];

        int buy = Integer.parseInt(strings[1]);
        int get = Integer.parseInt(strings[2]);
        LocalDateTime startTime = parseToLocalDateTime(strings[3]);
        LocalDateTime endTime = parseToLocalDateTime(strings[4]);
        return new PromotionData(name, buy, get, startTime, endTime);
    }

    public LocalDateTime parseToLocalDateTime(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date = LocalDate.parse(str, formatter);
        return date.atStartOfDay();
    }
}
