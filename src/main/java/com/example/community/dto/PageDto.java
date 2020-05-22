package com.example.community.dto;

import com.example.community.demo.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Package: com.example.community.dto
 * Description：
 * Author: weidongya
 * Date:  2020/3/26 17:50
 * Modified By:
 */
@Data
public class PageDto {
    private List<?> pages;
    private boolean nextPage;
    private boolean perviousPage;
    private  boolean firstPage;
    private boolean lastPage;
    private List<Integer> list = new ArrayList<>();
    private Integer count;
    private Integer index;

    public void setPage(Integer index,Integer count1,Integer size){

        if(count1%size==0) { this.count = count1 / size; }
        else{ this.count = count1/size + 1; }

        if(index>count){
            index = count;
        }else if(index<=0){
            index = 1;
        }else{
            this.index = index;
        }

        list.add(index);
        for (int i = index-1; i>=index-3 && i>0; i--) {
            list.add(0,i);
        }
        for (int i = index+1; i<=index+3 && i<=count; i++) {
            list.add(i);
        }

        if(index == 1){ perviousPage = false;
        }else{ perviousPage = true; }

        if(index == count ){ nextPage = false;
        }else{ nextPage = true; }

        if(list.contains(1)){ firstPage = false;
        }else{ firstPage = true; }

        if(list.contains(count)){ lastPage = false;
        }else{ lastPage = true; }
    }
}
