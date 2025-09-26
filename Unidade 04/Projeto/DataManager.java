package Projeto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static final String DATA_DIR = "Projeto/data";
    private static final String CLIENTES_FILE = DATA_DIR + "/clientes.json";
    private static final String PEDIDOS_FILE = DATA_DIR + "/pedidos.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void salvarDados(List<Cliente> clientes, List<Pedido> pedidos) {
        try {
            File dir = new File(DATA_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Salvar Clientes
            try (FileWriter writer = new FileWriter(CLIENTES_FILE)) {
                gson.toJson(clientes, writer);
            }

            // Salvar Pedidos
            try (FileWriter writer = new FileWriter(PEDIDOS_FILE)) {
                gson.toJson(pedidos, writer);
            }

        } catch (IOException e) {
            // Usar System.err para não travar a UI em caso de erro no fechamento
            System.err.println("### ERRO AO SALVAR DADOS ###");
            e.printStackTrace();
        }
    }

    public static List<Cliente> carregarClientes() {
        try {
            File file = new File(CLIENTES_FILE);
            if (!file.exists()) {
                return new ArrayList<>(); // Retorna lista vazia se o arquivo não existe
            }

            try (FileReader reader = new FileReader(file)) {
                Type tipoLista = new TypeToken<ArrayList<Cliente>>() {}.getType();
                List<Cliente> clientes = gson.fromJson(reader, tipoLista);
                return clientes != null ? clientes : new ArrayList<>();
            }
        } catch (IOException e) {
            System.err.println("### ERRO AO CARREGAR CLIENTES ###");
            e.printStackTrace();
            return new ArrayList<>(); // Retorna lista vazia em caso de erro
        }
    }

    public static List<Pedido> carregarPedidos() {
        try {
            File file = new File(PEDIDOS_FILE);
            if (!file.exists()) {
                return new ArrayList<>(); // Retorna lista vazia se o arquivo não existe
            }

            try (FileReader reader = new FileReader(file)) {
                Type tipoLista = new TypeToken<ArrayList<Pedido>>() {}.getType();
                List<Pedido> pedidos = gson.fromJson(reader, tipoLista);
                return pedidos != null ? pedidos : new ArrayList<>();
            }
        } catch (IOException e) {
            System.err.println("### ERRO AO CARREGAR PEDIDOS ###");
            e.printStackTrace();
            return new ArrayList<>(); // Retorna lista vazia em caso de erro
        }
    }
}
