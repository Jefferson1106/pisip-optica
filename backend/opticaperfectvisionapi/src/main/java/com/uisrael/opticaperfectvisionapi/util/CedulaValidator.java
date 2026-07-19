package com.uisrael.opticaperfectvisionapi.util;

public class CedulaValidator {

    public static String validarCedulaEcuatoriana(String cedula) {
        if (cedula == null || !cedula.matches("\\d{10}")) {
            return "La cédula debe contener exactamente 10 dígitos numéricos";
        }

        try {
            int provincia = Integer.parseInt(cedula.substring(0, 2));
            if (provincia < 1 || provincia > 24) {
                return "La cédula tiene un código de provincia inválido";
            }
        } catch (NumberFormatException e) {
            return "La cédula contiene caracteres no válidos";
        }

        int tercerDigito = Character.getNumericValue(cedula.charAt(2));
        if (tercerDigito >= 6) {
            return "El tercer dígito de la cédula es inválido";
        }

        int[] coeficientes = {2,1,2,1,2,1,2,1,2};
        int suma = 0;
        for (int i = 0; i < 9; i++) {
            int valor = Character.getNumericValue(cedula.charAt(i)) * coeficientes[i];
            if (valor >= 10) valor -= 9;
            suma += valor;
        }

        int digitoVerificador = (suma % 10 == 0) ? 0 : 10 - (suma % 10);
        if (digitoVerificador != Character.getNumericValue(cedula.charAt(9))) {
            return "La cédula no pasa la validación del dígito verificador";
        }

        return null; // null significa que la cédula es válida
    }
}
