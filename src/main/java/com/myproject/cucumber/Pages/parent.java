package com.myproject.cucumber.Pages;


import org.springframework.stereotype.Component;

@Component
 class parent {



    //AccessModifiers
    String didlikme = null; // same class, sam pakc sublass, and other class this is default
    public String broomsti = null; // Same class, sam pack - sub and non-sub class and diff pakc sub class and other classes
    protected String whereIam = null; // ame class, sam pack - sub and non-sub class and diff pakc sub class
    private final  String mygun = "10mmnullte model;"; // only applicable for same class and it  cane be accessbile any other classes


    //Primitive datatypes
    private static byte bytaval = 0;
    protected final boolean  blnflag = true;
    public static char chavle = 'D';
    private static double dbVale = 100.00d;
    private float flatvaue = 100f;
    private static long lngvla = 0;
    public static short shvle  = 100;
    public int invla = 100;
    public static String nameVale = null;

    public static String  staticMethod(String var1, int var2){
        String value = "the method is called  and it value is "+ var1 + var2;
        return value;
    }

    public void nonstaticMethod(String var1, int var2){
       System.out.println("the method is called  and it value is "+ var1 + var2);
    }


}
