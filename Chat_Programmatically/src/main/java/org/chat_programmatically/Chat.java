package org.chat_programmatically;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import dev.langchain4j.model.output.Response;

public class Chat {
    private static final String apiKey = System.getenv("OPENAI_API_KEY");
    private static final ChatLanguageModel chatModel = OpenAiChatModel.builder()
            .apiKey(apiKey)
            .modelName(OpenAiChatModelName.GPT_4_O_MINI)
            .build();
    public static void main(String[] args){
        chat_with_messages();
        chat_with_vision();
    }
    public static void chat_with_messages(){
        String response = chatModel.generate("""
                                What is the date today?""");
        System.out.println(response);
    }
    public static void chat_with_vision(){
        String image = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/62/Quercus_robur_acorns_in_Tuntorp_1.jpg/1920px-Quercus_robur_acorns_in_Tuntorp_1.jpg";
        Response<AiMessage> response = chatModel.generate(
                UserMessage.from(
                        ImageContent.from(image),
                        TextContent.from("What do you see")
                )
        );
        System.out.println(response.content().text());
    }
}
