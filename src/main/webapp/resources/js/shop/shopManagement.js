/**
 * 店铺管理页菜单展示
 */
$(function () {
    var shopId = parseUrl('shopId');
    var shopManagementUrl = "/o2o/shop/getShopManagementInfo?shopId=" + shopId;
    $.getJSON(shopManagementUrl, function (data) {
        if (data.redirect){
            window.location.href = data.url;
        }else {
            if (data.shopId != undefined && data.shopId != null){
                shopId = data.shopId;
            }
            $('#shop-info').attr('href', '/o2o/shop/operation?shopId=' + shopId);
        }
    });
})