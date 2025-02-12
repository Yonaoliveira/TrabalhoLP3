package controller;

import model.EmprestimoModel;
import repository.EmprestimoRepository;

public class EmprestimoController {
    private EmprestimoRepository emprestimoRepository = new EmprestimoRepository();

    public String salvar(EmprestimoModel emprestimoModel) {
        return emprestimoRepository.salvar(emprestimoModel);
    }
}
