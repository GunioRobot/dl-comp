package com.webshop.frontend.model

import _root_.net.liftweb.common.{Box,Full,Empty} 
import _root_.net.liftweb.util.Log
import com.webshop.frontend.restclient.RestClient

/**
 * Bean that holds information about an item  
 */
case class Item(var id: String, 
				var name: String, 
				var description: String, 
				var price: String, 
				var currency: String, var imgsSmall: List[String], var imgsLarge: List[String])

object Item {      
  def get(id: String): Box[Item] = RestClient.Items.get(id)
}
