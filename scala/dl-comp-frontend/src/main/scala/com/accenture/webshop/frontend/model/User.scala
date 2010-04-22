package com.accenture.webshop.frontend.model

import _root_.net.liftweb.mapper._
import _root_.net.liftweb.util._
import net.liftweb.http._ 
import com.accenture.webshop.frontend.logging._

object userName extends SessionVar[String]("")
object password extends SessionVar[String]("")
object isLoggedIn extends SessionVar[Boolean](false)

/**
 * The singleton that has methods for accessing the database
 */
object User extends User with MetaMegaProtoUser[User] with Logs {
  override def dbTableName = "users" // define the DB table name
  override def screenWrap = Full(<lift:surround with="default" at="content">
			       <lift:bind /></lift:surround>)
  // define the order fields will appear in forms and output
  override def fieldOrder = List(id, firstName, lastName, email,
  locale, timezone, password, textArea)

  // comment this line out to require email validations
  override def skipEmailValidation = true
  
  def getUserName: String = {    
	userName.get
  }
  
  def setLoggedIn(l: Boolean): Unit = {
    isLoggedIn.set(l)
  }
  
  def loggedIn: Boolean = {
    val str = "isLoggedIn = " + isLoggedIn.get
    debug(str)
    isLoggedIn.get
  }      
}

/**
 * An O-R mapped "User" class that includes first name, last name, password and we add a "Personal Essay" to it
 */
class User extends MegaProtoUser[User] {
  def getSingleton = User // what's the "meta" server

  // define an additional field for a personal essay
  object textArea extends MappedTextarea(this, 2048) {
    override def textareaRows  = 10
    override def textareaCols = 50
    override def displayName = "Personal Essay"
  }
}

