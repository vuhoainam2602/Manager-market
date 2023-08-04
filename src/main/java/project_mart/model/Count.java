package project_mart.model;


import javax.persistence.criteria.CriteriaBuilder;
import java.util.zip.CheckedOutputStream;

public class Count {
    private Integer count;

    public Count(){
    }
    public Count(Integer count){
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Count{" +
                "count=" + count +
                '}';
    }
}
