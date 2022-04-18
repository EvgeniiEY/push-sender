package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message

import java.io.FileInputStream


fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    sendNotificationLike()
    sendNotificationNewPost()

}

fun sendNotificationLike() {

    val message = Message.builder()
        .putData("action", "like")
        .putData("content", """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent())
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(message)
}

fun sendNotificationNewPost() {

    val message = Message.builder()
    .putData("action", "newPost")
        .putData(
            "content", """{
                "userId": 1,
                "userName": "Vasiliy",
                "postId": 2,
                "postContent": "Сериализация (Serialization) — это процесс, который переводит объект в последовательность байтов, по которой затем его можно полностью восстановить. Зачем это нужно? Дело в том, при обычном выполнении программы максимальный срок жизни любого объекта известен — от запуска программы до ее окончания. Сериализация позволяет расширить эти рамки и «дать жизнь» объекту так же между запусками программы.
Дополнительным бонусом ко всему является сохранение кроссплатформенности. Не важно какая у вас операционная система, сериализация переводит объект в поток байтов, который может быть восстановлен на любой ОС. Если вам необходимо передать объект по сети, вы можете сериализовать объект, сохранить его в файл и передать по сети получателю. Он сможет восстановить полученный объект. Так же сериализация позволяет осуществлять удаленный вызов методов (Java RMI), которые находятся на разных машинах с, возможно, разными операционными системами, и работать с ними так, словно они находятся на машине вызывающего java-процесса."
                
            """.trimIndent()
        )
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(message)
}
