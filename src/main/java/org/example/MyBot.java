package org.example;

import org.apache.commons.io.FileUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyBot extends TelegramLongPollingBot {

   List<TelegramUser> users= new ArrayList<>();
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            Message message = update.getMessage();
            String chatId =  update.getMessage().getChatId().toString();
            TelegramUser user =saveUser(chatId);
            if(message.hasText()){
               String text = message.getText();
               if(text.equals("/list")){
                   System.out.println(users);
               }
               if(text.equals("/start")){
                   if(user.getFullName()!=null){
                       try {
                           setLang(chatId,user);
                       } catch (TelegramApiException e) {
                           throw new RuntimeException(e);
                       }
                   }else{
                       SendMessage sendMessage = new SendMessage();
                       sendMessage.setText("Hi Welcome.\n"+
                               "Enter your name");
                       sendMessage.setChatId(chatId);
                       try {
                           execute(sendMessage);
                       } catch (TelegramApiException e) {
                           e.printStackTrace();
                       }
                       user.setStep(BotConstant.ENTER_NAME);
                   }


               }else if(user.getStep().equals(BotConstant.ENTER_NAME)){



                   try {
                       user.setFullName(text);
                       setLang(chatId,user);
                   } catch (TelegramApiException e) {
                       throw new RuntimeException(e);
                   }


                   user.setStep(BotConstant.SELECT_LANG);

               }else if(user.getStep().equals(BotConstant.WRITE_MSG)){
                   user.setMsg(text);
                   sendText(chatId,user.getSelectLang().equals(BotQuery.AZ_SELECT)?
                           "Admin sizinle elaqe quracaq":
                           "Admin will contact you soon"
                   );


               }
            }
//            Document document = message.getDocument();
//            List<PhotoSize> photo =message.getPhoto();
//            Voice voice = message.getVoice();
//
//            try {
//                saveFileToFolder(voice.getFileId(), "music.mp3");
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//            for (PhotoSize photoSize:photo) {
//                try {
//                    saveFileToFolder(photoSize.getFileId(), "test.png");
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
//            try {
//                saveFileToFolder(document.getFileId(),document.getFileName());
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }


        }
        else if (update.hasCallbackQuery()) {
            String chatId =update.getCallbackQuery().getFrom().getId().toString();
            String data = update.getCallbackQuery().getData();
            TelegramUser user =saveUser(chatId);
            if(user.getStep().equals(BotConstant.SELECT_LANG)){
                if(data.equals(BotQuery.AZ_SELECT)){
                    user.setSelectLang(BotQuery.AZ_SELECT);
                    sendText(chatId,"Az dili secildi");
                }else if(data.equals(BotQuery.EN_SELECT)){
                    user.setSelectLang(BotQuery.EN_SELECT);
                    sendText(chatId,"English language selected");
                }
                user.setStep(BotConstant.WRITE_MSG);
            }

        }
    }

    private void setLang(String chatId,TelegramUser user) throws TelegramApiException {
        SendMessage sendMessage =new SendMessage();
        sendMessage.setText(user.getFullName()+" Dil sec");
        sendMessage.setChatId(chatId);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> td = new ArrayList<>();


        InlineKeyboardButton inlineKeyboardButtonAz = new InlineKeyboardButton();
        inlineKeyboardButtonAz.setText("\uD83C\uDDE6\uD83C\uDDFF Az");
        inlineKeyboardButtonAz.setCallbackData(BotQuery.AZ_SELECT);

        InlineKeyboardButton inlineKeyboardButtonEn = new InlineKeyboardButton();
        inlineKeyboardButtonEn.setText("\uD83C\uDFF4\uDB40\uDC67\uDB40\uDC62\uDB40\uDC65\uDB40\uDC6E\uDB40\uDC67\uDB40\uDC7F En");
        inlineKeyboardButtonEn.setCallbackData(BotQuery.EN_SELECT);


        td.add(inlineKeyboardButtonAz);
        td.add(inlineKeyboardButtonEn);

        List<List<InlineKeyboardButton>> tr =new ArrayList<>();
        tr.add(td);

        inlineKeyboardMarkup.setKeyboard(tr);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        execute(sendMessage);
        user.setStep(BotConstant.SELECT_LANG);
    }

    private TelegramUser saveUser(String chatId){
        for (TelegramUser user:users) {
           if(user.getChatId().equals(chatId)){
               return user;
           }
        }
        TelegramUser user = new TelegramUser();
        user.setChatId(chatId);
        users.add(user);
        return user;
    }


    @Override
    public String getBotUsername() {
        return "azmovie1bot";
    }
    @Override
    public String getBotToken(){
        return "6239717222:AAG2SmXrNL8_eBnxrtwrSf_5EjMzOVheARE";
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    private void saveFileToFolder(String fileId,String fileName) throws Exception {
        GetFile getFile = new GetFile(fileId);
        File tgFile= execute(getFile);
        String fileUrl= tgFile.getFileUrl(getBotToken());
        System.out.println(fileUrl);

        URL url = new URL(fileUrl);
        InputStream inputStream =url.openStream();
        FileUtils.copyInputStreamToFile(inputStream, new java.io.File(fileName));
    }
    private void sendText(String chatId, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(chatId);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
