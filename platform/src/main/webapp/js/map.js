/**
 * Created with IntelliJ IDEA.
 * User: daixx
 * Date: 14-4-21
 * Time: 上午10:41
 * To change this template use File | Settings | File Templates.
 */
var map;
var town;
var emap,emap_lab,imgmap,imgmap_lab;
var xcemap,xcemapanno,xcimg,xcimganno,xc_xcz_img,xc_xcz_img2;

function initMap(){
    map = L.map('map',{
        crs: L.CRS.EPSG4326/*,
         fullscreenControl: { pseudoFullscreen: true}//全屏*/
    }).setView([centerLat, centerLng], centerZoom);
    xcemap = L.tileLayer('http://srv.tianditusx.cn/XCEMAP/wmts.asmx/WMTS?service=WMTS&request=GetTile&version=1.0.0&layer=XCEMAP&style=default&format=image/png&TileMatrixSet=TileMatrixSet0&TileMatrix={z}&TileRow={y}&TileCol={x}', {id: 'XCEMAP',minZoom:7,maxZoom:20});
    xcemapanno = L.tileLayer('http://srv.tianditusx.cn/XCEMAPANNO/wmts.asmx/WMTS?service=WMTS&request=GetTile&version=1.0.0&layer=XCEMAPANNO&style=default&format=image/png&TileMatrixSet=TileMatrixSet0&TileMatrix={z}&TileRow={y}&TileCol={x}', {id: 'XCEMAPANNO',minZoom:7,maxZoom:20});

    xcimg = L.tileLayer('http://srv.tianditusx.cn/xcimg/wmts.asmx/WMTS?service=WMTS&request=GetTile&version=1.0.0&layer=XCIMG&style=default&format=image/jpeg&TileMatrixSet=TileMatrixSet0&TileMatrix={z}&TileRow={y}&TileCol={x}', {id: 'XCIMG',minZoom:7,maxZoom:20});
    xcimganno = L.tileLayer('http://srv.tianditusx.cn/xcimganno/wmts.asmx/WMTS?service=WMTS&request=GetTile&version=1.0.0&layer=XCIMGANNO&style=default&format=image/png&TileMatrixSet=TileMatrixSet0&TileMatrix={z}&TileRow={y}&TileCol={x}', {id: 'XCIMGANNO',minZoom:7,maxZoom:20});

    emap = L.tileLayer('http://t0.tianditu.com/vec_c/wmts?service=WMTS&request=GetTile&version=1.0.0&layer=vec&style=default&format=&TileMatrixSet=c&TileMatrix={z}&TileRow={y}&TileCol={x}', {id: 'emap',minZoom:7,maxZoom:20/*, attribution: "<a href='javascript:freshMap();'>刷新</a>&nbsp;&nbsp;"+dataCopyRight*/});
    emap_lab   = L.tileLayer('http://t0.tianditu.com/cva_c/wmts?service=WMTS&request=GetTile&version=1.0.0&layer=cva&style=default&format=&TileMatrixSet=c&TileMatrix={z}&TileRow={y}&TileCol={x}', {id: 'emap_lab', minZoom:7,maxZoom:20/*,attribution:"<a href='javascript:freshMap();'>刷新</a>&nbsp;&nbsp;"+dataCopyRight*/});
    imgmap   = L.tileLayer('http://t0.tianditu.com/img_c/wmts?service=WMTS&request=GetTile&version=1.0.0&layer=img&style=default&format=&TileMatrixSet=c&TileMatrix={z}&TileRow={y}&TileCol={x}', {id: 'imgmap', minZoom:7,maxZoom:20/*,attribution:"<a href='javascript:freshMap();'>刷新</a>&nbsp;&nbsp;"+dataCopyRight*/});
    imgmap_lab   = L.tileLayer('http://t0.tianditu.com/cia_c/wmts?service=WMTS&request=GetTile&version=1.0.0&layer=cia&style=default&format=&TileMatrixSet=c&TileMatrix={z}&TileRow={y}&TileCol={x}', {id: 'imgmap_lab',minZoom:7,maxZoom:20/*, attribution: "<a href='javascript:freshMap();'>刷新</a>&nbsp;&nbsp;"+dataCopyRight*/});
    var town_bou   = L.tileLayer('http://42.121.35.57:8084/services/wmts/xc_zxcz_bou?service=WMTS&request=GetTile&version=1.0.0&layer=xc_zxcz_bou&style=default&format=&TileMatrixSet=c&TileMatrix={z}&TileRow={y}&TileCol={x}', {id: 'xc_zxcz_bou',minZoom:7,maxZoom:20/*, attribution: "<a href='javascript:freshMap();'>刷新</a>&nbsp;&nbsp;"+dataCopyRight*/});
    xc_xcz_img   = L.tileLayer('http://42.121.35.57:8084/services/wmts/xc_xcz_img?service=WMTS&request=GetTile&version=1.0.0&layer=xc_xcz_img&style=default&format=&TileMatrixSet=c&TileMatrix={z}&TileRow={y}&TileCol={x}', {id: 'xc_xcz_img',minZoom:7,maxZoom:20/*, attribution: "<a href='javascript:freshMap();'>刷新</a>&nbsp;&nbsp;"+dataCopyRight*/});
    xc_xcz_img2   = L.tileLayer('http://42.121.35.57:8084/services/wmts/xc_xcz_img2?service=WMTS&request=GetTile&version=1.0.0&layer=xc_xcz_img2&style=default&format=&TileMatrixSet=c&TileMatrix={z}&TileRow={y}&TileCol={x}', {id: 'xc_xcz_img2',minZoom:7,maxZoom:20/*, attribution: "<a href='javascript:freshMap();'>刷新</a>&nbsp;&nbsp;"+dataCopyRight*/});
    town   = L.tileLayer('http://42.121.35.57:8084/services/wmts/xc_zxcz?service=WMTS&request=GetTile&version=1.0.0&layer=xc_zxcz&style=default&format=png&TileMatrixSet=esritilematirx&TileMatrix={z}&TileRow={y}&TileCol={x}', {id: 'xc_zxcz',minZoom:7,maxZoom:20
        ,opacity:1/*, attribution: "<a href='javascript:freshMap();'>刷新</a>&nbsp;&nbsp;"+dataCopyRight*/});
    var baseMaps = {
        "电子地图": emap,
        "影像地图": imgmap
    };

    var baseImgmapLab = {
        "影像注记": imgmap_lab
    };
    var xcXczImg = {
        "大市聚镇高清影像": xc_xcz_img,
        "新昌县高清影像": xc_xcz_img2
    };
    L.control.layers(baseMaps,xcXczImg).addTo(map);
    baseMaps.影像地图.addTo(map);
    town_bou.addTo(map);
    town_bou.setZIndex(5);
    xc_xcz_img.setZIndex(7);
    xc_xcz_img2.setZIndex(8);

    map.on('mousemove', function(e) {
        $("div.leaflet-bottom.leaflet-left").html("鼠标所在位置："+(e.latlng.lng.toFixed(4))+" "+(e.latlng.lat.toFixed(4)));
        $("div.leaflet-bottom.leaflet-left").css({"border-radius":"5px","background":"rgba(255, 255, 255, 0.7)","padding":"5px"});
    });

    map.on('baselayerchange', function(e) {
        var zoomVal = map.getZoom();
        if(map.hasLayer(imgmap)){
            map.removeLayer(emap);
            map.removeLayer(emap_lab);
            if(zoomVal>14){
                map.addLayer(imgmap_lab);
                imgmap_lab.setZIndex(2);//数值越大，图层越靠上
            }
            //baseImgmapLab.影像注记.addTo(map);
            if(zoomVal>18){
                map.removeLayer(xcemap);
                map.removeLayer(xcemapanno);
                xcimg.addTo(map);
                xcimg.setZIndex(3);
                xcimganno.addTo(map);
                xcimganno.setZIndex(4);
            }
        }else{
            map.removeLayer(imgmap);
            map.removeLayer(imgmap_lab);
            if(zoomVal>14){
                map.addLayer(emap_lab);
                emap_lab.setZIndex(2);//数值越大，图层越靠上
            }
            if(zoomVal>18){
                map.removeLayer(xcimg);
                map.removeLayer(xcimganno);
                xcemap.addTo(map);
                xcemap.setZIndex(3);
                xcemapanno.addTo(map);
                xcemapanno.setZIndex(4);
            }
        }
    });

    map.on("zoomend", this.zoomChangeLayers, this);

}

function zoomChangeLayers(){
    var zoomVal = map.getZoom();
    if(zoomVal>18){
        if(map.hasLayer(emap)){
            //map.removeLayer(emap);
            //map.removeLayer(emap_lab);
            xcemap.addTo(map);
            xcemap.setZIndex(3);
            xcemapanno.addTo(map);
            xcemapanno.setZIndex(4);
        }else if(map.hasLayer(imgmap)){
            //map.removeLayer(imgmap);
            //map.removeLayer(imgmap_lab);
            xcimg.addTo(map);
            xcimg.setZIndex(3);
            xcimganno.addTo(map);
            xcimganno.setZIndex(4);
        }
    }else if(zoomVal<=18){
        if(map.hasLayer(xcemap)){
            map.removeLayer(xcemap);
            map.removeLayer(xcemapanno);
            emap.addTo(map);
            emap.setZIndex(3);
            if(zoomVal>14){
                emap_lab.addTo(map);
                emap_lab.setZIndex(4);
            }
        }else if(map.hasLayer(xcimg)){
            map.removeLayer(xcimg);
            map.removeLayer(xcimganno);
            imgmap.addTo(map);
            imgmap.setZIndex(3);
            if(zoomVal>14){
                imgmap_lab.addTo(map);
                imgmap_lab.setZIndex(4);
            }
        }else if(map.hasLayer(emap)){
            map.removeLayer(emap_lab);
            emap.addTo(map);
            emap.setZIndex(3);
            if(zoomVal>14){
                emap_lab.addTo(map);
                emap_lab.setZIndex(4);
            }
        }else if(map.hasLayer(imgmap)){
            map.removeLayer(imgmap_lab);
            imgmap.addTo(map);
            imgmap.setZIndex(3);
            if(zoomVal>14){
                imgmap_lab.addTo(map);
                imgmap_lab.setZIndex(4);
            }
        }
    }
}

