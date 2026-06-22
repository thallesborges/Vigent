package dev.thallesborges.NovaBuild.cliente.validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CnpjValidator {
    public static boolean isValido(Long cnpj) {

        // Transforma o texto em uma lista com todos os 14 dígitos originais
        List<Integer> digitosCnpj = new ArrayList<>(
                Long.toString(cnpj)
                        .chars()
                        .map(Character::getNumericValue)
                        .boxed()
                        .collect(Collectors.toList())
        );

        // Salva os dígitos verificadores reais informados no CNPJ
        int digitoUmVerificadorReal = digitosCnpj.get(12);
        int digitoDoisVerificadorReal = digitosCnpj.get(13);

        // Remove os 2 últimos dígitos para iniciar os cálculos
        digitosCnpj.subList(12, 14).clear();

        // --- CÁLCULO DO PRIMEIRO DÍGITO ---
        List<Integer> pesosUm = Arrays.asList(5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2);
        int digitoUmCalculado = calcularDigito(digitosCnpj, pesosUm);

        if (digitoUmCalculado != digitoUmVerificadorReal) {
            return false;
        }

        // Adiciona o primeiro dígito validado para poder calcular o segundo
        digitosCnpj.add(digitoUmCalculado);

        // --- CÁLCULO DO SEGUNDO DÍGITO ---
        List<Integer> pesosDois = Arrays.asList(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2);
        int digitoDoisCalculado = calcularDigito(digitosCnpj, pesosDois);

        if (digitoDoisCalculado != digitoDoisVerificadorReal) {
            return false;
        }

        return true;
    }

    // FUNÇÃO ISOLADA: Faz toda a matemática repetida do cálculo do dígito do CNPJ
    private static int calcularDigito(List<Integer> digitos, List<Integer> pesos) {
        int soma = 0;

        // Multiplica cada dígito pelo seu peso correspondente e acumula
        for (int i = 0; i < digitos.size(); i++) {
            soma += digitos.get(i) * pesos.get(i);
        }

        int resto = soma % 11;

        // Regra do CNPJ: se o resto for menor que 2, o dígito é 0. Caso contrário, 11 - resto.
        return (resto < 2) ? 0 : (11 - resto);
    }
}