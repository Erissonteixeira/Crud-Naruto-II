package io.github.Erissonteixeira.api_crudnarutoII.model;

import java.util.Random;

public class NinjaDeTaijutsu extends Personagem implements Ninja {

    private final Random random = new Random();

    public NinjaDeTaijutsu(String nome, int vida, int chakra){
        super(nome, vida, chakra);
    }

    @Override
    public void usarJutsu(String nomeJutsu, Personagem alvo) {
        Jutsu jutsu = getJutsusMap().get(nomeJutsu);

        if(jutsu == null){
            System.out.println(getNome() + " não possui o jutsu " + nomeJutsu);
            return;
        }

        if(getChakra() < jutsu.getConsumoChakra()){
            System.out.println(getNome() + " não tem chakra suficiente para usar " + nomeJutsu);
            return;
        }

        setChakra(getChakra() - jutsu.getConsumoChakra());

        alvo.setVida(alvo.getVida() - jutsu.getDano());

        System.out.println(getNome() + " usou " + nomeJutsu + " causando " + jutsu.getDano() +
                " de dano em " + alvo.getNome() + ". Chakra restante: " + getChakra());
    }

    @Override
    public void desviar(int danoRecebido) {

        boolean sucesso = random.nextBoolean();

        if(sucesso){
            System.out.println(getNome() + " conseguiu desviar do ataque!");
        } else {
            setVida(getVida() - danoRecebido);
            System.out.println(getNome() + " falhou ao desviar e recebeu " + danoRecebido + " de dano. Vida restante: " + getVida());
        }
    }
}
