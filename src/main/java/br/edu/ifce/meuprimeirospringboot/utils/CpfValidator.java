package br.edu.ifce.meuprimeirospringboot.utils;

public class CpfValidator {
    
    /**
     * Valida se um CPF é válido
     * @param cpf CPF a ser validado (pode conter pontos e traços)
     * @return true se o CPF for válido, false caso contrário
     */
    public static boolean isValid(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return false;
        }
        
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        
        if (cpfLimpo.length() != 11) {
            return false;
        }
        
        if (cpfLimpo.matches("(\\d)\\1{10}")) {
            return false;
        }
        
        int digito1 = calcularDigito(cpfLimpo.substring(0, 9));
        int digito2 = calcularDigito(cpfLimpo.substring(0, 9) + digito1);
        
        return cpfLimpo.equals(cpfLimpo.substring(0, 9) + digito1 + digito2);
    }
    
    /**
     * Calcula um dígito verificador do CPF
     * @param cpf CPF sem os dígitos verificadores
     * @return dígito verificador calculado
     */
    private static int calcularDigito(String cpf) {
        int soma = 0;
        int peso = cpf.length() + 1;
        
        for (int i = 0; i < cpf.length(); i++) {
            soma += Integer.parseInt(String.valueOf(cpf.charAt(i))) * peso;
            peso--;
        }
        
        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }
    
    /**
     * Formata um CPF válido (xxx.xxx.xxx-xx)
     * @param cpf CPF a ser formatado
     * @return CPF formatado ou null se inválido
     */
    public static String formatar(String cpf) {
        if (!isValid(cpf)) {
            return null;
        }
        
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        return cpfLimpo.substring(0, 3) + "." + 
               cpfLimpo.substring(3, 6) + "." + 
               cpfLimpo.substring(6, 9) + "-" + 
               cpfLimpo.substring(9, 11);
    }
    
    /**
     * Remove formatação de um CPF
     * @param cpf CPF formatado
     * @return CPF apenas com números
     */
    public static String limpar(String cpf) {
        if (cpf == null) {
            return null;
        }
        return cpf.replaceAll("[^0-9]", "");
    }
} 