package boat.golden.marketit.datatypes;

/**
 * Created by Vipin soni on 01-10-2018.
 */

public class ShopData {
    String shop_name,pic_add,shop_id,shop_type,tags,short_desc,type;
    float lat,lon;

    public ShopData(String shop_name, String pic_add, String shop_id, String shop_type, String tags, String short_desc, float lat, float lon, String type) {
        this.shop_name = shop_name;
        this.pic_add = pic_add;
        this.shop_id = shop_id;
        this.shop_type = shop_type;
        this.tags = tags;
        this.short_desc = short_desc;
        this.lat = lat;
        this.lon = lon;
        this.type=type;
    }

    public String getShop_name() {
        return shop_name;
    }

    public String getPic_add() {
        return pic_add;
    }

    public String getShop_id() {
        return shop_id;
    }

    public String getShop_type() {
        return shop_type;
    }

    public String getTags() {
        return tags;
    }

    public String getShort_desc() {
        return short_desc;
    }
    public String getType() {
        return type;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }
}
