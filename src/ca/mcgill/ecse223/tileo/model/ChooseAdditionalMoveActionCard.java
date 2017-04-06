/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

// line 799 "../../../../../TileO.ump"
public class ChooseAdditionalMoveActionCard extends ActionCard
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ChooseAdditionalMoveActionCard(String aInstructions, Deck aDeck)
  {
    super(aInstructions, aDeck);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

  // line 803 "../../../../../TileO.ump"
   public Game.Mode getActionCardMode(){
    return Game.Mode.GAME_CHOOSEADDITIONALMOVEACTIONCARD;
  }

}