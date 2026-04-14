package com.tugas.deploy.controller;

import com.tugas.deploy.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    // Menyimpan data secara temporary (tanpa database)
    private List<User> dataMahasiswa = new ArrayList<>();

    // Menampilkan halaman Login
    @GetMapping({"/", "/login"})
    public String showLoginPage() {
        return "login";
    }

    // Memproses request Login
    @PostMapping("/login")
    public String processLogin(@RequestParam String username, @RequestParam String password, Model model) {
        // Validasi: username = admin, password = nim masing-masing
        if ("admin".equals(username) && "20240140074".equals(password)) {
            return "redirect:/home";
        }
        model.addAttribute("error", "Username atau Password salah!");
        return "login";
    }

    // Menampilkan halaman Home beserta list data mahasiswa
    @GetMapping("/home")
    public String showHomePage(Model model) {
        // Melempar data list mahasiswa ke view HTML
        model.addAttribute("mahasiswaList", dataMahasiswa);
        return "home";
    }

    // Menampilkan halaman Form Input
    @GetMapping("/form")
    public String showFormPage(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }

    // Memproses data dari Form Input dan menyimpannya ke List temporary
    @PostMapping("/form")
    public String submitForm(@ModelAttribute User user) {
        dataMahasiswa.add(user);
        return "redirect:/home"; // Kembali ke home setelah data diinput
    }

    // Memproses Logout
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}