/**
 * 注册店铺 js
 * 1. 页面初始化时解析后台传来的 areaList 和 shopCategoryList
 * 2. 异步提交表单
 */
$(function () {
    var shopId = parseUrl("shopId");
    var isUpdate = shopId ? true : false;
    var shopInitUrl = '/o2o/shop/infoInit';
    var shopInfoUrl = '/o2o/shop/getShopInfo?shopId=' + shopId;
    var registerShopUrl = '/o2o/shop/registerShop';
    var modifyShopUrl = '/o2o/shop/modifyShop';
    getShopInitInfo();
    getShopInfo();
    function getShopInitInfo() {
        $.ajaxSettings.async = false;
        $.getJSON(shopInitUrl, function (data) {
            if (data.success){
                var tempShopCategoryHtml = '';
                var tempAreaHtml = '';
                data.shopCategoryList.map(function (item, index) {
                    tempShopCategoryHtml += '<option data-id="' + item.shopCategoryId + '">' +
                        item.shopCategoryName + '</option>'
                });
                data.areaList.map(function (item, index) {
                    tempAreaHtml += '<option data-id="' + item.areaId + '">' +
                        item.areaName + '</option>'
                });
                $('#shop-category').html(tempShopCategoryHtml);
                $('#area').html(tempAreaHtml);
            }else{
                $.toast('初始化店铺类别和区域信息失败！' + data.errMsg);
            }
        })
    }

    function getShopInfo() {
        $.ajaxSettings.async = true;
        $.getJSON(shopInfoUrl, function (data) {
            if(data.success){
                shop = data.shop;
                $('#shop-name').val(shop.shopName);
                $('#shop-desc').val(shop.shopDesc);
                $('#shop-addr').val(shop.shopAddr);
                $('#shop-phone').val(shop.phone);
                $('#shop-category option[data-id="' + shop.shopCategory.shopCategoryId + '"]').attr('selected', 'selected');
                $('#area option[data-id="' + shop.area.areaId + '"]').attr('selected', 'selected');
                $('#shop-category').attr('disabled', 'disabled');
            }
        })
    }


    $('#submit').click(function () {
        var shop = {};
        if (isUpdate){
            shop.shopId = shopId;
        }
        shop.shopName = $('#shop-name').val();
        shop.shopDesc = $('#shop-desc').val();
        shop.shopAddr = $('#shop-addr').val();
        shop.phone = $('#shop-phone').val();
        shop.shopCategory = {
            shopCategoryId : $('#shop-category').find('option').not(function () {
                return !this.selected;
            }).data('id')
        };
        shop.area = {
            areaId : $('#area').find('option').not(function () {
                return !this.selected;
            }).data('id')
        };
        var shopImg = $('#shop-img')[0].files[0];
        var formData = new FormData();
        formData.append('shopImg', shopImg);
        formData.append('shopStr', JSON.stringify(shop));
        var verifyCodeActual = $('#j_captcha').val();
        if (!verifyCodeActual){
            $.toast('请输入验证码！')
        }
        formData.append('verifyCodeActual', verifyCodeActual);
        $.ajax({
            url:(isUpdate ? modifyShopUrl : registerShopUrl),
            type:'POST',
            data:formData,
            contentType:false,
            processData:false,
            cache:false,
            success:function (data) {
                if (data.success){
                    $.toast('提交成功');
                }else {
                    $.toast('提交失败！' + data.errMsg);
                }
                $('#captcha_img').click();
            }
        });
    });
})

function changeVerifyCode(img) {
    img.src = '../Kaptcha?' + Math.floor(Math.random() * 100);
}

function parseUrl(name){
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null){
        return decodeURIComponent(r[2]);
    }
    return '';
}