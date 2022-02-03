package org.zerock.guestbook.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.guestbook.dto.GuestbookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.service.GuestbookService;

@Controller
@RequestMapping("/guestbook")
@Log4j2
@RequiredArgsConstructor        //final이나 @NotNull이 붙은 것들 자동 주입
public class GuestbookController {

    private final GuestbookService guestbookService;    //final 선언

    @GetMapping("/")
    public String index(){
        return "redirect:/guestbook/list";
    }

    //경로 2개 설정할 때 Get Mapping

    /*@GetMapping({"/","/list"})
    public String list(){
        log.info("list..................");
        return "/guestbook/list";
    }*/

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("list ... ... ... ..."+ pageRequestDTO);

        model.addAttribute("result", guestbookService.getList(pageRequestDTO));

    }

    @GetMapping("/register")
    public void register(){
        log.info("register get...");
    }

    @PostMapping("/register")
    public String registerPost(GuestbookDTO dto, RedirectAttributes redirectAttributes){
        log.info("dto........"+dto);

        //새로 추가된 엔티티의 번호
        Long gno = guestbookService.register(dto);

        redirectAttributes.addFlashAttribute("msg",gno);
        return "redirect:/guestbook/list";
    }



}
