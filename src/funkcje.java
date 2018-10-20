
import java.io.File;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author michał
 */
public class funkcje {
    private funkcje(){}
    public static void main(String...args){
        new Aplikacja().setVisible(true);
    }


/**
 * 
 * @param source
 * @param regex
 * @param string
 * @return 
 */
    protected static String zamień(String source,String[]regex,String[]string){
//DEBUGzamień
     String błąd="";
    if(0==regex.length*string.length)błąd+= "Proszę okreslić podmiot edycji.\n";
    if(source==null||source.equals(""))błąd+="Proszę wprowadzić tekst wejściowy.\n";
//INITzamień 
        int max=Math.max(regex.length,string.length );
           //,min=Math.min(regex.length,string.length);
//MOTORzamień
        source=source.replaceAll(regex[0],string[0]);
        String s="",t="";
        for(int re=1;re<=max;re++){
            if(regex.length>re)t=regex[re];
            if(string.length>re)s=string[re];
       
            
            source=source.replaceAll(t,s);
        }
        
//OUTPUTzamień
        return source;
    }
    /** w budowie
     * @param source
     * @param regex
     * @param string
     * @param index
     * @param regexFlags
     * @return 
     */
    protected static String wstaw(String source, String[] regex, int regexFlags, String[] string, int index, boolean endOfGroup){
//INPUT CHECK: oba pola argumentów muszą mieć wartości
        if(regex.length*string.length==0) return "Wszystkie pola muszą mieć wartość";
        StringBuilder sb=new StringBuilder(source);
        
//INIT
        Matcher mat=Pattern.compile(regex[0],regexFlags).matcher(source);
        int min=Math.min(regex.length,string.length)-1, max=Math.max(regex.length,string.length)-1;
        
        
        for(int i=0,ind=index;    i<max;  mat=(regex.length>=++i?Pattern.compile(regex[i],regexFlags).matcher(source):mat)   ){
            
            
            if (mat.find()){
               if(!endOfGroup){
                   while(mat.start()<index
                           ){sb.insert(mat.start(),string[i]);mat.find();
                   }
                   
                }
                
                while(mat.find()){
                    try{
                      sb.insert(ind, string[i]);
                  
                    }catch(ArrayIndexOutOfBoundsException aiobe){
                    sb.insert(mat.end(), string[i]);
                    }
                }
            }
        }
    
 //OUTPUT
        return sb.toString()+(
                min==max
                ?""
                :("następujące argumenty nie zostały wykorzystane:\n"+Arrays.toString(Arrays.copyOfRange(    (string.length>regex.length?string:regex)   ,min,max-1))+"\n\n"));
    }
    /**W budowie
     * 
     * @param in
     * @param regex
     * @return 
     */
    protected static String wyodrębnij(String in,String[]regex){
        
        return "wyodrębnianie niedostępne w aktualnej wersji";
    }
    /**
     * podstaw("Ala ma kota","ma","_","i __ _ją ").equals("Ala i mama mają kota");
     * @param wejście tekst źródłowy
     * @param regex dla zlokalizowania konsumowanych treści; jest jednocześnie wartością podstawianą pod zmienną
     * @param zmienna tekst, który będzie zastąpiony we wzorze
     * @param wzór po podstawieniu zmiennej zastąpi wartość regexów w odpowiednich miejscach
     * @return Zmieniony tekst źródłowy
     */
    protected static String podstaw(String wejście,String []regex,String[]zmienna,String []wzór){
//INITpodstaw
        Matcher mat;
        
        int lL=regex.length, lŚ=zmienna.length,lP=wzór.length;
        if(lL*lL*lP==0)return "brak jednej składowej";
        int min=Math.min(lL, Math.min(lŚ,lP))
            , max=Math.max(lL,Math.max(lŚ,lP));
        
//MOTORpodstaw
        for(int i=0;i<min;i++){
            
            (mat=Pattern.compile(regex[i]).matcher(wejście)).find();
            
            do{
                wejście=
                        wejście.replace(
                                mat.group()
                               ,wzór[i].replace(
                                        zmienna[i]
                                       ,mat.group()));
            } while (mat.find(mat.end()));
            
        }  
        
//OUTPUTodstaw
            return 
                    Arrays.toString(Arrays.copyOfRange( regex,min,lL))
                    + Arrays.toString(Arrays.copyOfRange( zmienna,min,lŚ))
                    + Arrays.toString(Arrays.copyOfRange( wzór,min,lP))
                    +"\n\n\n"
                    +wejście;
    }
    /**
     * Służy do gromadzenia plików danej lokalizacji
     * @param storagePath katalog z plikami
     * @return  lista nazw plików
     */
    protected static String[] importuj(String storagePath ){
//INITimportuj
        File[] fs=new File(storagePath).listFiles();
        String[] s=new String[fs.length];
//MOTORimportuj
        for(int i=0;i<fs.length;i++)    s[i]=fs[i].exists()?fs[i].getName():"------";
//OUTPUTimportuj
        return s;
    }
    
    
    
}
