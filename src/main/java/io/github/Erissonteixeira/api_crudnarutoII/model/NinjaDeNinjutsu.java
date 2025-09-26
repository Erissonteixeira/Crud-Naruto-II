package io.github.Erissonteixeira.api_crudnarutoII.model;

import java.util.Random;

public class NinjaDeNinjutsu extends Personagem implements Ninja {

    private final Random random = new Random();

    public NinjaDeNinjutsu(String nome, int vida, int chakra) {
        super(nome, vida, chakra);
    }

    @Override
    public void usarJutsu(String nomeJutsu, Personagem alvo) {
        if (isDerrotado()) {
            System.out.println(getNome() + " está derrotado e não pode atacar!");
            return;
        }

        Jutsu jutsu = getJutsusMap().get(nomeJutsu);
        if (jutsu == null) {
            System.out.println(getNome() + " não possui o jutsu " + nomeJutsu);
            return;
        }

        if (getChakra() < jutsu.getConsumoChakra()) {
            System.out.println(getNome() + " não tem chakra suficiente para usar " + nomeJutsu);
            return;
        }

        setChakra(getChakra() - jutsu.getConsumoChakra());
        int danoFinal = (int) (jutsu.getDano() * 1.1); // bônus de 10%
        alvo.setVida(alvo.getVida() - danoFinal);

        System.out.println(getNome() + " usou " + nomeJutsu + " causando " + danoFinal +
                " de dano em " + alvo.getNome() + ". Chakra restante: " + getChakra());

        if (alvo.isDerrotado()) {
            System.out.println(alvo.getNome() + " foi derrotado!");
        }
    }

    @Override
    public void desviar(int danoRecebido) {
        boolean sucesso = random.nextInt(100) < 40; // 40% chance
        if (sucesso) {
            System.out.println(getNome() + " conseguiu desviar do ataque!");
        } else {
            setVida(getVida() - danoRecebido);
            System.out.println(getNome() + " falhou ao desviar e recebeu " + danoRecebido +
                    " de dano. Vida restante: " + getVida());

            if (isDerrotado()) {
                System.out.println(getNome() + " foi derrotado!");
            }
        }
    }
}
