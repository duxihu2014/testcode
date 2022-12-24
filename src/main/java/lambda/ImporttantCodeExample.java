package lambda;

import java.util.HashMap;
import java.util.Map;

public class ImporttantCodeExample {


    public static void main(String[] args) {


        Map<String, String> param = new HashMap<>();
        param.put("aaa","bbb");
        param.put("aaa1","bbb1");
        param.put("aaa2","bbb2");
        int i = 0;
        for (Map.Entry<String, String> entry : param.entrySet()) {
            param.put(entry.getValue(), entry.getKey());
        }

        for (String value : param.values()) {

        }

        for (Map.Entry<String,String> entry: param.entrySet()) {

        }
        String [] array1,array2;
//        for (int j = 0; j < array2.length; j++) {
//            String s = array2[j];
//
//        }
        StringBuffer buffer = new StringBuffer();


        String pointCut ="public .* com.gupaoedu.vip.demo.service..*Service..*(.*)";
        //把Spring的Excpress变成Java能够识别的正则表达式
        pointCut = pointCut
                .replaceAll("\\.", "\\\\.");
        pointCut = pointCut
                .replaceAll("\\\\.\\*", ".*");
        pointCut = pointCut
                .replaceAll("\\(", "\\\\(");
        pointCut = pointCut
                .replaceAll("\\)", "\\\\)");


    }


}




//
//0.1
//        return vehicleList.stream().filter(v -> v.getVin().equals(vehicle.getVin())).findFirst()
//        .map(SprVehicle::getModelCoding);
//
//
//        0.2
//private Map<String, String> getZookeeperDataPoints() {
//        try {
//        return Arrays
//        .asList(objectMapper.readValue(zkClient.getData().forPath(PATH), DataPointsWithServiceId[].class))
//        .stream()
//        .collect(Collectors.toMap(DataPointsWithServiceId::getId, DataPointsWithServiceId::getHexCode));
//        } catch (JsonMappingException | JsonParseException exception) {
//        LOG.error("Parsing data point list has failed", exception);
//        } catch (Exception exception) {
//        LOG.error("Reading from zookeeper has failed, error", exception);
//        }
//        return new HashMap<>();
//        }
//
//
//        1.
//public BigDecimal getPrice(LocalDateTime dateTime) {
//        return peakTimeMultipliers.stream()
//        .filter(multiplier -> multiplier.dayOfWeek.equals(dateTime.getDayOfWeek()))
//        .findFirst()
//        .map(multiplier -> unitRate.multiply(multiplier.multiplier))
//        .orElse(unitRate);
//        }
//
//
//        2.
//        Random readingRandomiser = new Random();
//        for (int i = 0; i < number; i++) {
//        double positiveRandomValue = Math.abs(readingRandomiser.nextGaussian());
//        BigDecimal randomReading = BigDecimal.valueOf(positiveRandomValue).setScale(4, RoundingMode.CEILING);
//        ElectricityReading electricityReading = new ElectricityReading(now.minusSeconds(i * 10), randomReading);
//        readings.add(electricityReading);
//        }
//
//        readings.sort(Comparator.comparing(ElectricityReading::getTime));
//
//        3.
//        return Optional.of(pricePlans.stream().collect(
//        Collectors.toMap(PricePlan::getPlanName, t -> calculateCost(electricityReadings.get(), t))));
//        }
//
//
//        4.
//
//        rivate BigDecimal calculateCost(List<ElectricityReading> electricityReadings, PricePlan pricePlan) {
//        BigDecimal average = calculateAverageReading(electricityReadings);
//        BigDecimal timeElapsed = calculateTimeElapsed(electricityReadings);
//
//        BigDecimal averagedCost = average.divide(timeElapsed, RoundingMode.HALF_UP);
//        return averagedCost.multiply(pricePlan.getUnitRate());
//        }
//
//private BigDecimal calculateAverageReading(List<ElectricityReading> electricityReadings) {
//        BigDecimal summedReadings = electricityReadings.stream()
//        .map(ElectricityReading::getReading)
//        .reduce(BigDecimal.ZERO, (reading, accumulator) -> reading.add(accumulator));
//
//        return summedReadings.divide(BigDecimal.valueOf(electricityReadings.size()), RoundingMode.HALF_UP);
//        }
//
//private BigDecimal calculateTimeElapsed(List<ElectricityReading> electricityReadings) {
//        ElectricityReading first = electricityReadings.stream()
//        .min(Comparator.comparing(ElectricityReading::getTime))
//        .get();
//        ElectricityReading last = electricityReadings.stream()
//        .max(Comparator.comparing(ElectricityReading::getTime))
//        .get();
//
//        return BigDecimal.valueOf(Duration.between(first.getTime(), last.getTime()).getSeconds() / 3600.0);
//        }
//
//        5.
//@Bean
//public Map<String, List<ElectricityReading>> perMeterElectricityReadings() {
//final Map<String, List<ElectricityReading>> readings = new HashMap<>();
//final ElectricityReadingsGenerator electricityReadingsGenerator = new ElectricityReadingsGenerator();
//        smartMeterToPricePlanAccounts()
//        .keySet()
//        .forEach(smartMeterId -> readings.put(smartMeterId, electricityReadingsGenerator.generate(20)));
//        return readings;
//        }
//
//        6.
//@Test
//public void givenMeterIdAndLimitShouldReturnRecommendedCheapestPricePlans() throws JsonProcessingException {
//        String smartMeterId = "bob";
//        populateMeterReadingsForMeter(smartMeterId);
//
//        ResponseEntity<String> response =
//        restTemplate.getForEntity("/price-plans/recommend/" + smartMeterId + "?limit=2", String.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        }EndpointTest