package lambda;

public class TestStream2 {

//    验证非空的  Optional
//    只要你把stream用好，其他的一些细节的都包含在内了
//            可以说java的整个核心就是stream
//
//
//    Map<String, List<ResPickOrderDetail>> collect = details.stream().collect(Collectors.groupingBy(ResPickOrderDetail::getProductCode));
//
//    int productCount = details.stream().collect(Collectors.groupingBy(ResPickOrderDetail::getProductCode)).size();
//
//resPage.getResult().stream().filter(e -> StringUtils.isNotBlank(e.getMergeOrderNo())).forEach(resPickedOrder -> {
//        orderNoLists.addAll(resPickedOrder.getPickOrders().stream().map(ResPickOrder::getOrderNo).collect(Collectors.toList()));
//    });
//
//return entry.getValue().stream().map(ResPickOrderDetail::getOutOfStockQuantity).reduce(BigDecimal.ZERO, BigDecimal::add).intValue();
//
//entry.getValue().stream().mapToLong(p -> p.getReleaseTime().getTime()).min().getAsLong();
//
//waitPickList.stream().sorted(Comparator.comparing(ResPickingOrderVo::getReleaseTime)).collect(Collectors.toList());
//
//    Optional<ResLocationProductStorageCell> cellOptional =
//            productStorageCellList.stream().filter(item -> item.getProductCode().equals(productCode)).findFirst();
//
// cellOptional.isPresent() ? cellOptional.get().getStorageCellCode() : StringUtils.EMPTY;
}
