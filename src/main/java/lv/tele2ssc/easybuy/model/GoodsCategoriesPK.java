package lv.tele2ssc.easybuy.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GoodsCategoriesPK implements Serializable{
    
    @Column(name = "goods_id")
    private Long goodsId;

    @Column(name = "category_id")
    private Long categoryId;
       
    
}
