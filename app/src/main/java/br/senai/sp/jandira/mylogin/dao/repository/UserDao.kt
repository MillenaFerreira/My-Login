package br.senai.sp.jandira.mylogin.dao.repository

import androidx.room.*
import br.senai.sp.jandira.mylogin.model.User

//Contrato
@Dao
interface UserDao {
    //salvar o usuario no banco de dados
    @Insert
    fun save(user: User): Long

    //atualizar o usuario no banco
    //esse int me mostra quantos usuarios seram atualizados no banco
    @Update
    fun update(user: User): Int

    //deletar o usuario do banco
    @Delete
    fun delete(user: User): Int

    //função para buscar usuario que esta se cadastrando pelo email no banco
    //se o email nao estiver cadastrado cria-se um novo usuario
    @Query("SELECT * FROM tbl_user WHERE email = :email")
    fun findUserByEmail(email: String): User

    //funcao que busca no banco de dados o usuario correto
    @Query("SELECT * FROM tbl_user WHERE email = :email AND password = :password")
    fun authenticate(email: String, password: String): User
}