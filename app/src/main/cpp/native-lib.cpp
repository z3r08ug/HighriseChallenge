#include <jni.h>
#include <string>
#include <map>
#include <vector>
#include <android/log.h>
#include "MovieController.hpp"

#define APPNAME "highrisechallenge"
#define LOGD(TAG) __android_log_print(ANDROID_LOG_DEBUG, APPNAME, TAG);

extern "C" JNIEXPORT jobject
JNICALL Java_chris_example_com_highrisechallenge_activities_MainActivity_loadMovieData(
        JNIEnv* env,
        jobject obj) {
    movies::MovieController controller;

    /*Populate jobjectArray of Movie objects as is needed for Movie Controller constructor.
    */
    std::vector<movies::Movie*> movies = controller.getMovies();

    jclass clz = (*env).FindClass("chris/example/com/highrisechallenge/pojo/Movie");
    jmethodID ctr = (*env).GetMethodID(clz, "<init>", "(Ljava/lang/String;I)V");

    jobjectArray objArray = (env)->NewObjectArray(movies.size(), clz, NULL);

    int movieSize = movies.size();

    for(int i = 0; i < movies.size(); i++)
    {
        jobject object = (*env).NewObject(clz, ctr, env->NewStringUTF((movies[i]->name).c_str()), movies[i]->lastUpdated);
        (env)->SetObjectArrayElement(objArray, i, object);
    }
    (*env).DeleteLocalRef(clz);

    //create HashMap of Movie Details

    jclass clazz = env->FindClass("java/util/HashMap");
    jmethodID init = env->GetMethodID(clazz, "<init>", "()V");
    jobject detailsMap = env->NewObject(clazz, init);

    jmethodID putMethod = env->GetMethodID(
                                           clazz,
                                           "put",
                                           "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"
                                           );

    jclass detailClass = (*env).FindClass("chris/example/com/highrisechallenge/pojo/MovieDetail");
    jmethodID detailConstructor = (*env).GetMethodID(detailClass, "<init>", "(Ljava/lang/String;F[Lchris/example/com/highrisechallenge/pojo/Actor;Ljava/lang/String;)V");

    jclass actorClass = (*env).FindClass("chris/example/com/highrisechallenge/pojo/Actor");
    jmethodID actorConstructor = (*env).GetMethodID(actorClass, "<init>", "(Ljava/lang/String;ILjava/lang/String;)V");

    //load details and actors
    for (int i = 0; i < movies.size(); i++)
    {
        jstring key = env->NewStringUTF((movies[i]->name).c_str());
        movies::MovieDetail* detail = controller.getMovieDetail(movies[i]->name);

        //iterate through vector of actors to populate jobjectArray of Actors
        jobjectArray actorArray = (env)->NewObjectArray(3, actorClass, NULL);
        for(int j = 0; j < detail->actors.size(); j++)
        {
            jobject object = (*env).NewObject(actorClass, actorConstructor, env->NewStringUTF(detail->actors[j].name.c_str()), detail->actors[j].age, env->NewStringUTF(detail->actors[j].imageUrl.c_str()));
            (env)->SetObjectArrayElement(actorArray, j, object);
        }

        //create a detail object to be placed in the hash map
        jobject value = (*env).NewObject(detailClass, detailConstructor, env->NewStringUTF(detail->name.c_str()), detail->score, actorArray, env->NewStringUTF(detail->description.c_str()));

        env->CallObjectMethod(detailsMap, putMethod, key, value);
    }


    //create a MovieController jobject to be sent to Java
    jclass movieControllerClass = (*env).FindClass("chris/example/com/highrisechallenge/pojo/MovieController");
    jmethodID movieControllerConstructor = (*env).GetMethodID(movieControllerClass, "<init>", "([Lchris/example/com/highrisechallenge/pojo/Movie;Ljava/util/HashMap;)V");
    jobject movieController = (*env).NewObject(movieControllerClass, movieControllerConstructor, objArray, detailsMap);


    return movieController;
}