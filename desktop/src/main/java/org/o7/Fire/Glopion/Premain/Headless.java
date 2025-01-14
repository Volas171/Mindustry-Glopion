package org.o7.Fire.Glopion.Premain;

import Atom.Reflect.Reflect;
import mindustry.mod.ModClassLoader;
import org.o7.Fire.Glopion.Brain.AparapiBenchmark;
import org.o7.Fire.Glopion.Brain.TrainingJeneticData;
import org.o7.Fire.Glopion.Experimental.WebhookStandalone;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

public class Headless {
    //run configuration
    //Classpath: Mindustry-Glopion.desktop.test
    //Class org.o7.Fire.Glopion.Premain.Run
    //JVM: 16
    public static void main(String[] args) {
        if(System.getenv("DiscordWebhook") != null){
            try {
                WebhookStandalone standalone = new WebhookStandalone(System.getenv("DiscordWebhook"));
                System.setOut(standalone.asPrintStream());
                System.out.println("Test");
            }catch(MalformedURLException e){
                e.printStackTrace();
            }
        }
        if(Reflect.debug){
            System.out.println("Mindustry Jar Classloader: " + MindustryLauncher.class.getClassLoader().getClass().getCanonicalName());
            System.out.println("Current Jar Classloader: " + ModClassLoader.class.getClassLoader().getClass().getCanonicalName());
        }
        System.out.println("Args: " + Arrays.toString(args));
        List<String> arg = Arrays.asList(args);
        if(arg.contains("training")){
            System.out.println("training ?");
            try {
                TrainingJeneticData.main(args);
            }catch(Throwable throwable){
                throwable.printStackTrace();
            }
        }else if(arg.contains("logging")){
            System.out.println("logging");
        }else if(arg.contains("benchmark")){
            AparapiBenchmark.main(args);
        }
    }
}
