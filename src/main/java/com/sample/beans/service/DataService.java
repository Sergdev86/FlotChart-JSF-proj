package com.sample.beans.service;

import com.google.gson.Gson;
import com.sample.beans.model.Data;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.*;

@ManagedBean(eager=true)
@ApplicationScoped
public class DataService implements Serializable{

    private List<Data> dataList = new ArrayList<Data>();
    private String jsonData;
    private boolean generationDisabled = false;
    private Date addedDate;
    private Gson gson = new Gson();


    @ManagedProperty(value = "#{data}")
    private Data dataObj;


    public DataService(){

    }


    public Data getDataObj() {
        return dataObj;
    }

    public void setDataObj(Data dataObj) {
        this.dataObj = dataObj;
    }

    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {this.dataList = dataList;}

    public String getJsonData() {return jsonData;}

    public void setJsonData(String jsonData) {this.jsonData = jsonData;}


    public void addData(){
        Data newDataObj = new Data();
        long date = addedDate.getTime();
        if(isDateExist(date)) showErrorMessage();
        else {
            newDataObj.setId(Math.abs(dataObj.hashCode()));
            newDataObj.setDate(date);
            newDataObj.setValue(dataObj.getValue());
            newDataObj.setDescription(dataObj.getDescription());
            System.out.println(newDataObj.toString());
            dataList.add(newDataObj);
            dataList.sort(Comparator.comparing(Data::getDate));
            convertToJson();
            generationDisabled = true;
            showSuccessMessage();
        }
    }


    private boolean isDateExist(long date) {

        /*   Проверяем добавленную дату на совпадение с уже имеющимися   */
        return dataList.stream().filter(o -> o.getDate() == date).findFirst().isPresent();
    }


    public boolean isGenerationDisabled() {return generationDisabled;}

    public void setGenerationDisabled(boolean generationDisabled) {this.generationDisabled = generationDisabled;}

    public Date getAddedDate() {return addedDate;}

    public void setAddedDate(Date addedDate) {this.addedDate = addedDate;}


    public void generate(){
        long value;
        long dateLong;
        String dateDescription;

        Data dataObj;
        Calendar calendar = new GregorianCalendar();

//	Генерируем случайную дату в пределах 2010-2015гг
        calendar.set(calendar.YEAR, randBetween(2017, 2017));
        calendar.set(calendar.DAY_OF_YEAR, randBetween(1, calendar.getActualMaximum(calendar.DAY_OF_YEAR)));

        for(int i = 0; i < 50; i++){
            dataObj = new Data();

//Увеличиваем дату на 1 день
            calendar.add(Calendar.DATE, 1);
            dateDescription = calendar.getTime().toString();
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            dateLong = calendar.getTimeInMillis();
            dataObj.setDate(dateLong);
            value = (long)(Math.random()*100);
            dataObj.setValue(value);
            dataObj.setDescription(dateDescription);
            dataObj.setId(Math.abs(dataObj.hashCode()));
            dataList.add(dataObj);
        }
        convertToJson();
        generationDisabled = true;
    }

    private int randBetween(int start, int end) {
        return (int)((Math.random() * (end-start))+start);
    }


    private void convertToJson(){
        jsonData = "";
        jsonData = gson.toJson(getListOfPoints());
    }


    private List<long[]> getListOfPoints(){
        List<long[]> points = new ArrayList<>();
        for(Data d : dataList){
            long[] pointData = {d.getDate(), d.getValue()};
            points.add(pointData);
        }
        return points;
    }

    public void clear(){
        dataList.clear();
        jsonData = "";
        generationDisabled = false;
    }

    public void showErrorMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Дата уже существует! Измените дату!"));
    }

    public void showSuccessMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Успешное сохранение.", ""));
    }
}
