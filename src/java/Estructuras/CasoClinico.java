/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author Alexis
 */
public class CasoClinico extends AbstractBinary{

    private BinarySearchTree Examenes;
    private BinarySearchTree Medicamentos;
    
    public CasoClinico(String nombre){
        this.name = nombre;
        this.Examenes = new BinarySearchTree();
        this.Medicamentos = new BinarySearchTree();
    }
    
    @Override
    public String getInfo()
    {   
        if (!(Medicamentos.displayIn().equals(""))){
            return Examenes.displayIn() +" ,"+ Medicamentos.displayIn();
        }
        else{
            return Examenes.displayIn() +" , "+ Medicamentos.displayIn();
        }
    }

    
    public void addExamenes(String examen, String precio)
    {
        if (!(Examenes.isMember(examen))) {
            Examen newExamen = new Examen(examen, precio);    
            Examenes.insert(newExamen);
        }
    }
    public void addMedicamentos(String medicamento, String precio)
    {
        if (!(Medicamentos.isMember(medicamento))){
            Medicamento newMedicamento = new Medicamento(medicamento, precio);
            Medicamentos.insert(newMedicamento);
        }
    }
    public void removeExamenen(String examen)
    {
        if (Examenes.isMember(examen)) {
            Examenes.delete(examen);
        }
    }
    public void removeMedicamento(String medicamento)
    {
        if (Medicamentos.isMember(medicamento)){
            Medicamentos.delete(medicamento);
        }
    }
    
    
    
    
}
