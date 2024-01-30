package acsse.csc3a.gui;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;

import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

import acsse.csc3a.graph.ETYPEOFHELP;
import acsse.csc3a.graph.Family;
import acsse.csc3a.graph.GraphCreation;
import acsse.csc3a.graph.Node;
import acsse.csc3a.graph.ShortestPath;
import acsse.csc3a.graph.Sponsor;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.*;

public class GraphPane extends StackPane {

	static GraphCanvas canvas = null;
	BorderPane layout =null; 
	Vertex<Node> start =null;
	Vertex<Node> end=null;

	/**
	 * 
	 */
	public GraphPane() {
		canvas = new GraphCanvas();
		layout= new BorderPane();

		//draw the canvas
		canvas.draw();


		//buttons
		Button btnAddNode =  new Button("ADD NODE");
		Button btnDeleteNode = new Button("DELETE NODE");
		Button btnUpdateNode = new Button("UPDATE NODE");
		Button btnFindSponsor = new Button("FIND THE SPONSOR");

		//creating a grid for the buttons
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10));
		grid.setHgap(8);
		grid.setVgap(8);

		GridPane.setConstraints(btnAddNode,0,0);
		GridPane.setConstraints(btnDeleteNode,1,0);
		GridPane.setConstraints(btnUpdateNode,2,0);
		GridPane.setConstraints(btnFindSponsor,3,0);

		grid.getChildren().addAll(btnAddNode,btnDeleteNode,btnUpdateNode,btnFindSponsor);
		getChildren().add(grid);
		
		
		/*
		 * Find sponsor
		 * */
		btnFindSponsor.setOnAction(e->{
			
			/*
			 * List of sponsors
			 * */
			List<String> options1 = new ArrayList<>();
			for(Graph.Vertex<Node> node : GraphCreation.nodes) {
				if(node.getValue() instanceof Sponsor) {
					options1.add(node.getValue().getName());
				}
			}

			ChoiceDialog<String> dialog1 = new ChoiceDialog<>("--choose below--",options1);
			dialog1.setTitle("from (Sponsor)");
			dialog1.setHeaderText("Select an option:");
			dialog1.setContentText("Nodes:");
			Optional<String> result = dialog1.showAndWait();
			result.ifPresent(selectedOption1 -> {
				
				for(Graph.Vertex<Node> node : GraphCreation.nodes) {
					if(node.getValue() instanceof Sponsor) {
						if(node.getValue().getName().equals(selectedOption1)) {
							start= node ;
						}
					}
				}
				
			});
			
			/*
			 * List of Famalies
			 * */
			List<String> options = new ArrayList<>();
			for(Graph.Vertex<Node> node : GraphCreation.nodes) {
				if(node.getValue() instanceof Family) {
					options.add(node.getValue().getName());
				}
			}

			ChoiceDialog<String> dialog = new ChoiceDialog<>("--choose below--",options);
			dialog.setTitle("From (Family)");
			dialog.setHeaderText("Select an option:");
			dialog.setContentText("Nodes:");
			Optional<String> result1 = dialog.showAndWait();
			result1.ifPresent(selectedOption1 -> {
				
				for(Graph.Vertex<Node> node : GraphCreation.nodes) {
					if(node.getValue() instanceof Family) {
						if(node.getValue().getName().equals(selectedOption1)) {
							end= node;
						}
					}
				}
				
			});
			
			
			/*
			 * Perfom the algorithm
			 * */
			List<Vertex<Node>> nodes = ShortestPath.findShortestPath(start,end);
			System.out.print("Shortest Path:");
			for(Vertex<Node> node:nodes) {
				System.out.print(node.getValue().getName()+" ->");
			}
			
			/*
			 * Displaying
			 * */
			
			Dialog<ButtonType> btndialog = new Dialog<>();
			btndialog.setTitle("Shortest Path");
			
			DialogPane dialogPane = btndialog.getDialogPane();

	        dialogPane.setPrefWidth(450);

			// Create text fields
			TextField txtPath= new TextField();
			txtPath.setPrefWidth(400);
			

			// Create grid pane and add text fields to it
			GridPane gridPane = new GridPane();
			gridPane.add(new Label("Path: "), 0, 0);
			gridPane.add(txtPath, 1, 0);
			
			for(Vertex<Node> node:nodes) {
				txtPath.appendText(node.getValue().getName()+" ->");
			}
			
			

			// Set the custom content of the dialog
			btndialog.getDialogPane().setContent(gridPane);

			// Add OK and Cancel buttons to the dialog
			btndialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
			
			btndialog.showAndWait();
			
		});
		

		/*
		 * Creating a Node
		 * */
		btnAddNode.setOnAction(e->{

			List<String> options = new ArrayList<>();
			options.add("Family");
			options.add("Sponsor");

			ChoiceDialog<String> dialog = new ChoiceDialog<>("Family", options);
			TextInputDialog textdialog = new TextInputDialog();
			dialog.setTitle("Create Node");
			dialog.setHeaderText("Select an option:");
			dialog.setContentText("Nodes:");

			Optional<String> result = dialog.showAndWait();
			result.ifPresent(selectedOption -> {
				System.out.println("Selected option: " + selectedOption);
				if(selectedOption.equals("Family")) {
					Optional<String> Surname = inputbox("Create Node","Enter the surname","Surname :",textdialog);
					System.out.println("Surname:"+Surname.get());

					Optional<String> numMembers = inputbox("Create Node","Enter the number of family members","Members :",textdialog);
					System.out.println("Members:"+numMembers.get());
					/*
					 * type of help the family needs
					 * */
					List<String> TypeOfHelp = new ArrayList<>();
					TypeOfHelp.add("Food");
					TypeOfHelp.add("Shelter");
					TypeOfHelp.add("Financial");

					ChoiceDialog<String> help = new ChoiceDialog<>("Food", TypeOfHelp);
					help.setTitle("Type of help the family needs");
					help.setHeaderText("Select an option:");
					help.setContentText("Help:");

					Optional<String> resultofhelp = help.showAndWait();
					resultofhelp.ifPresent(selectedHelp->{
						if(selectedHelp.equals("Financial")) {

							Optional<String> x = inputbox("Create Node","Enter the X co-ordinate"," X co-ordinate :",textdialog);
							Optional<String> y = inputbox("Create Node","Enter the Y co-ordinate"," Y co-ordinate :",textdialog);

							Family fam = new Family(Integer.parseInt(numMembers.get()),ETYPEOFHELP.FINANCIAL,Surname.get(),Integer.parseInt(x.get()),Integer.parseInt(y.get()));
							Graph.Vertex<Node> famNode = new Graph.Vertex<Node>(fam);
							GraphCreation.nodes.add(famNode);

						}else if(selectedHelp.equals("Food")) {
							Optional<String> x = inputbox("Create Node","Enter the X co-ordinate"," X co-ordinate :",textdialog);
							Optional<String> y = inputbox("Create Node","Enter the Y co-ordinate"," Y co-ordinate :",textdialog);

							Family fam = new Family(Integer.parseInt(numMembers.get()),ETYPEOFHELP.FOOD,Surname.get(),Integer.parseInt(x.get()),Integer.parseInt(y.get()));
							Graph.Vertex<Node> famNode = new Graph.Vertex<Node>(fam);
							GraphCreation.nodes.add(famNode);

						}else if(selectedHelp.equals("Shelter")) {
							Optional<String> x = inputbox("Create Node","Enter the X co-ordinate"," X co-ordinate :",textdialog);
							Optional<String> y = inputbox("Create Node","Enter the Y co-ordinate"," Y co-ordinate :",textdialog);

							Family fam = new Family(Integer.parseInt(numMembers.get()),ETYPEOFHELP.SHELTER,Surname.get(),Integer.parseInt(x.get()),Integer.parseInt(y.get()));
							Graph.Vertex<Node> famNode = new Graph.Vertex<Node>(fam);
							GraphCreation.nodes.add(famNode);

						}
					});


				}else if(selectedOption.equals("Sponsor")) {
					Optional<String> nameOfSponsor = inputbox("Create Node","Enter the Name Of Sponsor","Name :",textdialog);
					System.out.println("Name of Sponsor:"+nameOfSponsor.get());

					List<String> TypeOfHelp = new ArrayList<>();
					TypeOfHelp.add("Food");
					TypeOfHelp.add("Shelter");
					TypeOfHelp.add("Financial");

					ChoiceDialog<String> help = new ChoiceDialog<>("Food", TypeOfHelp);
					help.setTitle("Type of help the Sponsor is willing to offer");
					help.setHeaderText("Select an option:");
					help.setContentText("Help:");

					Optional<String> resultofhelp = help.showAndWait();

					resultofhelp.ifPresent(selectedhelp->{
						if(selectedhelp.equals("Financial")) {

							Optional<String> x = inputbox("Create Node","Enter the X co-ordinate"," X co-ordinate :",textdialog);
							Optional<String> y = inputbox("Create Node","Enter the Y co-ordinate"," Y co-ordinate :",textdialog);

							Sponsor sponsor = new Sponsor(nameOfSponsor.get(),ETYPEOFHELP.FINANCIAL,Integer.parseInt(x.get()),Integer.parseInt(y.get()));
							Graph.Vertex<Node> SponsorNode = new Graph.Vertex<Node>(sponsor);

							GraphCreation.nodes.add(SponsorNode);


						}else if(selectedhelp.equals("Shelter")) {
							Optional<String> x = inputbox("Create Node","Enter the X co-ordinate"," X co-ordinate :",textdialog);
							Optional<String> y = inputbox("Create Node","Enter the Y co-ordinate"," Y co-ordinate :",textdialog);

							Sponsor sponsor = new Sponsor(nameOfSponsor.get(),ETYPEOFHELP.SHELTER,Integer.parseInt(x.get()),Integer.parseInt(y.get()));
							Graph.Vertex<Node> SponsorNode = new Graph.Vertex<Node>(sponsor);

							GraphCreation.nodes.add(SponsorNode);

						}else if(selectedhelp.equals("Food")) {
							Optional<String> x = inputbox("Create Node","Enter the X co-ordinate"," X co-ordinate :",textdialog);
							Optional<String> y = inputbox("Create Node","Enter the Y co-ordinate"," Y co-ordinate :",textdialog);

							Sponsor sponsor = new Sponsor(nameOfSponsor.get(),ETYPEOFHELP.FOOD,Integer.parseInt(x.get()),Integer.parseInt(y.get()));
							Graph.Vertex<Node> SponsorNode = new Graph.Vertex<Node>(sponsor);

							GraphCreation.nodes.add(SponsorNode);

						}
					});
				}
			});
			//update draw
			drawNode();
		});


		/*
		 * Delete Node
		 * */
		btnDeleteNode.setOnAction(e->{

			//search for that node and remove it in the grid
			TextInputDialog textdialog = new TextInputDialog();
			Optional<String> x = inputbox("Delete Node","Enter the X co-ordinate"," X co-ordinate :",textdialog);
			Optional<String> y = inputbox("Delete Node","Enter the Y co-ordinate"," Y co-ordinate :",textdialog);


			int numNode=0;
			ArrayList<Graph.Vertex<Node>> nodes = GraphCreation.getNodes();
			for(Graph.Vertex<Node> node :nodes) {
				if(node.getValue() instanceof Family) {
					if((node.getValue().getX()==Integer.parseInt(x.get())) && (node.getValue().getY()==Integer.parseInt(y.get()))) {
						GraphCreation.nodes.remove(numNode);
						GraphCanvas.RemoveNode(node.getValue().getX(),node.getValue().getY());
						return;
					}

				}else if(node.getValue() instanceof Sponsor){
					if((node.getValue().getX()==Integer.parseInt(x.get())) && (node.getValue().getY()==Integer.parseInt(y.get()))) {
						GraphCreation.nodes.remove(numNode);
						GraphCanvas.RemoveNode(node.getValue().getX(),node.getValue().getY());
						return;
					}
				}
				numNode+=1;
				System.out.println(numNode);
			}

			drawNode();

		});

		/*
		 * Update Node
		 * */
		btnUpdateNode.setOnAction(e->{

			//type of node to be updated
			List<String> nodesoptions = new ArrayList<>();
			nodesoptions.add("Family");
			nodesoptions.add("Sponsor");
			ChoiceDialog<String> typenodedialog = new ChoiceDialog<>("Family",nodesoptions);
			typenodedialog.setTitle("update Node");
			typenodedialog.setHeaderText("Select type of node to update:");
			typenodedialog.setContentText("Nodes:");

			Optional<String> result = typenodedialog.showAndWait();
			result.ifPresent(selectedOption -> {
				/*
				 * Type of node choosen
				 * */
				if(selectedOption.equals("Family")) {
					//choose the node you want to update
					List<String> options = new ArrayList<>();
					for(Graph.Vertex<Node> node : GraphCreation.nodes) {
						if(node.getValue() instanceof Family) {
							options.add(node.getValue().getName());
						}
					}

					ChoiceDialog<String> dialog = new ChoiceDialog<>("--choose below--",options);
					dialog.setTitle("update Node");
					dialog.setHeaderText("Select an option:");
					dialog.setContentText("Nodes:");

					Optional<String> result1 = dialog.showAndWait();
					result1.ifPresent(selectedOption1 -> {
						//update the node
						Dialog<ButtonType> btndialog = new Dialog<>();
						btndialog.setTitle("Update Family Node");
						btndialog.setHeaderText("Enter values:");

						// Create text fields
						TextField txtSurname= new TextField();
						TextField txtNumFamily = new TextField();
						TextField txtX = new TextField();
						TextField txtY = new TextField();

						ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList(
								"Financial", "Shelter", "Food"));


						// Create grid pane and add text fields to it
						GridPane gridPane = new GridPane();
						gridPane.add(new Label("Surname:"), 0, 0);
						gridPane.add(txtSurname, 1, 0);
						gridPane.add(new Label("Number Members:"), 0, 1);
						gridPane.add(txtNumFamily, 1, 1);
						gridPane.add(new Label("X Coordinate:"), 0, 2);
						gridPane.add(txtX, 1, 2);
						gridPane.add(new Label("Y Coordinate:"), 0, 3);
						gridPane.add(txtY, 1, 3);
						gridPane.add(new Label("Type of Help:"), 0, 4);
						gridPane.add(comboBox, 1, 4);


						//search
						for(Graph.Vertex<Node> node : GraphCreation.nodes) {
							if(node.getValue() instanceof Family) {
								if(node.getValue().getName().equals(selectedOption1)) {
									Family tempFam = (Family) node.getValue() ;
									txtSurname.appendText(node.getValue().getName());
									txtNumFamily.appendText(Integer.toString(tempFam.getNumMembers()));
									txtX.appendText(Integer.toString(node.getValue().getX()));
									txtY.appendText(Integer.toString(node.getValue().getY()));

									System.out.println("type of help:"+String.valueOf(node.getValue().getTypeOfhelp()));

									if(tempFam.getTypeOfhelp().equals(ETYPEOFHELP.FOOD)) {
										comboBox.setValue("Food");
									}else if(node.getValue().getTypeOfhelp().equals(ETYPEOFHELP.SHELTER)) {
										comboBox.setValue("Shelter");
									}else if(node.getValue().getTypeOfhelp().equals(ETYPEOFHELP.FINANCIAL)) {
										comboBox.setValue("Financial");
									}
								}
							}
						}

						// Set the custom content of the dialog
						btndialog.getDialogPane().setContent(gridPane);

						// Add OK and Cancel buttons to the dialog
						btndialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

						// Wait for the user to close the dialog
						btndialog.showAndWait().ifPresent(buttonType -> {
							if (buttonType == ButtonType.OK) {
								// Retrieve values from the text fields
								String Surname = txtSurname.getText();
								String numFam = txtNumFamily.getText();
								String x = txtX.getText();
								String y = txtY.getText();

								//update the node
								//search
								for(Graph.Vertex<Node> node : GraphCreation.nodes) {
									if(node.getValue() instanceof Family) {
										if(node.getValue().getName().equals(selectedOption1)) {
											Family tempFam = (Family) node.getValue() ;
											node.getValue().setName(Surname);
											tempFam.setNumMembers(Integer.parseInt(numFam));
											node.getValue().setX(Integer.parseInt(x));
											node.getValue().setY(Integer.parseInt(y));
											String selectedtype = comboBox.getValue();
											node.getValue().setTypeOfhelp(ETYPEOFHELP.valueOf(selectedtype.toUpperCase()));	
										}
									}
								}

								// Perform actions with the retrieved values
								System.out.println("Surname: " + Surname);
								System.out.println("numFam: " + numFam);
								System.out.println("{x,y}: " + "{"+x+","+y+"}");
							}
						});
					});

					/*
					 * Update for sponsor
					 * */	
				}else if(selectedOption.equals("Sponsor")) {
					//choose the node you want to update
					List<String> options = new ArrayList<>();
					for(Graph.Vertex<Node> node : GraphCreation.nodes) {
						if(node.getValue() instanceof Sponsor) {
							options.add(node.getValue().getName());
						}
					}

					ChoiceDialog<String> dialog = new ChoiceDialog<>("--choose below--",options);
					dialog.setTitle("update Sponsor Node");
					dialog.setHeaderText("Select an option:");
					dialog.setContentText("Nodes:");

					Optional<String> result1 = dialog.showAndWait();
					result1.ifPresent(selectedOption1 -> {
						//update the node
						Dialog<ButtonType> btndialog = new Dialog<>();
						btndialog.setTitle("Update Node");
						btndialog.setHeaderText("Enter values:");

						// Create text fields
						TextField txtName= new TextField();
						TextField txtX = new TextField();
						TextField txtY = new TextField();

						ComboBox<String> comboBox = new ComboBox<>(FXCollections.observableArrayList(
								"Financial", "Shelter", "Food"));


						// Create grid pane and add text fields to it
						GridPane gridPane = new GridPane();
						gridPane.add(new Label("Name of Sponsor:"), 0, 0);
						gridPane.add(txtName, 1, 0);
						gridPane.add(new Label("X Coordinate:"), 0, 1);
						gridPane.add(txtX, 1, 1);
						gridPane.add(new Label("Y Coordinate:"), 0, 2);
						gridPane.add(txtY, 1, 2);
						gridPane.add(new Label("Type of Help willing to offer: "), 0, 3);
						gridPane.add(comboBox, 1, 3);

						//search
						for(Graph.Vertex<Node> node : GraphCreation.nodes) {
							if(node.getValue() instanceof Sponsor) {
								if(node.getValue().getName().equals(selectedOption1)) {
									Sponsor tempSponsor = (Sponsor) node.getValue() ;
									txtName.appendText(node.getValue().getName());
									txtX.appendText(Integer.toString(node.getValue().getX()));
									txtY.appendText(Integer.toString(node.getValue().getY()));

									//comboBox.setValue("Food");
									System.out.println("type of help:"+String.valueOf(node.getValue().getTypeOfhelp()));


									if(tempSponsor.getTypeOfhelp().equals(ETYPEOFHELP.FOOD)) {
										comboBox.setValue("Food");
									}else if(node.getValue().getTypeOfhelp().equals(ETYPEOFHELP.SHELTER)) {
										comboBox.setValue("Shelter");
									}else if(node.getValue().getTypeOfhelp().equals(ETYPEOFHELP.FINANCIAL)) {
										comboBox.setValue("Financial");
									}
								}
							}
						}

						// Set the custom content of the dialog
						btndialog.getDialogPane().setContent(gridPane);

						// Add OK and Cancel buttons to the dialog
						btndialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

						// Wait for the user to close the dialog
						btndialog.showAndWait().ifPresent(buttonType -> {
							if (buttonType == ButtonType.OK) {
								// Retrieve values from the text fields
								String Name = txtName.getText();
								String x = txtX.getText();
								String y = txtY.getText();

								//update the node
								//search
								for(Graph.Vertex<Node> node : GraphCreation.nodes) {
									if(node.getValue() instanceof Sponsor) {
										if(node.getValue().getName().equals(selectedOption1)) {
											//Sponsor tempSponsor = (Sponsor) node.getValue() ;
											node.getValue().setName(Name);
											node.getValue().setX(Integer.parseInt(x));
											node.getValue().setY(Integer.parseInt(y));
											String selectedtype = comboBox.getValue();
											node.getValue().setTypeOfhelp(ETYPEOFHELP.valueOf(selectedtype.toUpperCase()));

										}
									}
								}

								// Perform actions with the retrieved values
								System.out.println("Name: " + Name);
								System.out.println("{x,y}: " + "{"+x+","+y+"}");
							}
						});

					});
				}			
			});
			drawNode();
		});

		//setting the layout
		layout = new BorderPane();
		layout.setCenter(canvas);
		layout.setBottom(grid);
		getChildren().add(layout);
	}

	/**
	 * the Input box to get infomation from the user
	 * 
	 * @param title
	 * @param Question
	 * @param Content
	 * @param dialog
	 * @return results
	 */
	public Optional<String> inputbox(String title,String Question,String Content,TextInputDialog dialog){
		dialog = new TextInputDialog();
		dialog.setTitle(title);
		dialog.setHeaderText(Question);
		dialog.setContentText(Content);

		// show the dialog and wait for the user to enter a value
		Optional<String> result = dialog.showAndWait();
		return result;	
	}

	/**
	 * Draws the Nodes {used when updating the grapg}
	 */
	public void drawNode() {
		ArrayList<Graph.Vertex<Node>> nodes = GraphCreation.getNodes();

		for(Graph.Vertex<Node> node :nodes) {
			if(node.getValue() instanceof Family) {
				GraphCanvas.DN(Color.GREEN,node.getValue().getX(),node.getValue().getY(),node.getValue().getName());
				System.out.println("Family "+node.getValue().getX()+":"+node.getValue().getY()+":"+node.getValue().getName());
			}else if(node.getValue() instanceof Sponsor){
				GraphCanvas.DN(Color.YELLOW,node.getValue().getX(),node.getValue().getY(),node.getValue().getName());
				System.out.println("Sponsor "+node.getValue().getX()+":"+node.getValue().getY()+":"+node.getValue().getName());
			}
		}
	}

}
