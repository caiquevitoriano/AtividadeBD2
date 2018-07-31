/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividadebanco;

import controle.ProdutoDAO;
import java.util.ArrayList;
import java.util.Scanner;
import modelo.Produto;

/**
 *
 * @author caique
 */
public class AtividadeBanco {

    private static final Scanner scan = new Scanner(System.in);
    ProdutoDAO dao = new ProdutoDAO();
    
    public static void main(String[] args) {
        
        ArrayList<Produto> produtos = new ArrayList();
        int op = 0;
        
       while(op != 3){
           
            System.out.println("---------------------------------");
            System.out.println("1. Para inserir um novo produto");
            System.out.println("2. Para buscar um produto");
            System.out.println("3. Para sair");
            System.out.print("Digite a opção: ");
            op = scan.nextInt();

            switch (op){
                case 1:
                    System.out.println("---------------------------------");
                    addProduto();
                    break;
                case 2:
                    System.out.println("---------------------------------");                    
                    buscarRedis();
                    break;
                case 3:
                    System.out.println("Tchau...");
                    break;
                default:
                    System.out.println("Escolha uma opção valida");
                    System.out.println("---------------------------------");
                    break;
            }        
       }           
        
        
        
    }
    
    private static Produto addProduto(){
        
        Produto prod = new Produto();
        
        System.out.print("Digite o codigo do produto: ");
        prod.setCodigo(scan.nextInt());
      
        System.out.print("Digite a desrição do produto: ");
        prod.setDescricao(scan.next());
        
        System.out.print("Digite o preço do produto: ");
        prod.setPreco(scan.nextFloat());
        
        ProdutoDAO dao = new ProdutoDAO();
        dao.salvarProduto(prod);
        System.out.println("Produto salvo!");       
        
        return prod;
        
    }
    
    private static void buscarRedis(){
       
        System.out.print("Digite o codigo do produto: ");
        int codigo = scan.nextInt();       
       
        
        ProdutoDAO dao = new ProdutoDAO();        
        dao.buscarRedis(codigo);
        
        
        
    }
    
    /*
    private static void buscarProduto(){
       
              
        System.out.print("Digite o codigo do produto: ");
        int codigo = scan.nextInt();
        
        ProdutoDAO dao = new ProdutoDAO();
        System.out.println(dao.buscar(codigo));
        
             
        
    } */
    
}
